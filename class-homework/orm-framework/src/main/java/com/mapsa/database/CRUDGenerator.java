package com.mapsa.database;

import com.mapsa.persistence.Column;
import com.mapsa.persistence.Table;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author esmaeil
 * @date 01.11.20
 */
public class CRUDGenerator {
    DBConnection dbConnection = DBConnection.getInstance();

    public void create(Object object) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            Table table = object.getClass().getDeclaredAnnotation(Table.class);
            String query = "create table " + table.name() + " (";
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Column) {
                        Column column = field.getAnnotation(Column.class);
                        query += column.name() + " " + column.dataType() + "(" + column.length() + "),";
                    }
                }
            }
            if (query.endsWith(",")) {
                query = query.substring(0, query.length() - 1);
            }
            query += ")";
            System.out.println(query);
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Object object) throws IOException, IllegalAccessException, NoSuchFieldException, SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            Table table = object.getClass().getDeclaredAnnotation(Table.class);
            StringBuilder query = new StringBuilder("UPDATE " + table.name() + " SET ");
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                if (column != null) {

                    query.append(column.name() + " " + " = ");
                    if (field.getType().getSimpleName().endsWith("String")) {
                        query.append("'").append(field.get(object)).append("',");
                    } else query.append(field.get(object)).append(",");
                }

            }
            if (query.toString().trim().endsWith(",")) {
                query = new StringBuilder(query.substring(0, query.length() - 1));
            }
            query.append(" WHERE ");
            Field id = object.getClass().getDeclaredField("id");
            id.setAccessible(true);
            Column column = id.getAnnotation(Column.class);
            if (column == null) {
                return;
            } else {

                query.append(column.name() + " = " + id.get(object));
                PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
                preparedStatement.executeUpdate();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void delete(Object object) throws IOException, IllegalAccessException, NoSuchFieldException, SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            Table table = object.getClass().getDeclaredAnnotation(Table.class);
            StringBuilder query = new StringBuilder("DELETE FROM " + table.name() + " WHERE ");
            Field id = object.getClass().getDeclaredField("id");
            id.setAccessible(true);
            Column column = id.getAnnotation(Column.class);
            if (column == null) {
                return;
            } else {
                query.append(column.name() + " = " + id.get(object));
                PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
                preparedStatement.execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Object load(Object object) throws IOException, NoSuchFieldException, IllegalAccessException, SQLException {
        try (Connection connection = dbConnection.getConnection()) {

            Table table = object.getClass().getDeclaredAnnotation(Table.class);
            StringBuilder query = new StringBuilder("SELECT * FROM " + table.name() + " WHERE ");
            Field id = object.getClass().getDeclaredField("id");
            id.setAccessible(true);
            Column column = id.getAnnotation(Column.class);
            if (column != null) {
                query.append(column.name() + " = " + id.get(object));
            }
            PreparedStatement statement = connection.prepareStatement(query.toString());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                column = field.getAnnotation(Column.class);
                if (column != null) {
                    Object fieldValue = resultSet.getObject(column.name());
                    field.set(object, fieldValue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public void save(Object object) throws IOException, IllegalAccessException, SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            Table table = object.getClass().getDeclaredAnnotation(Table.class);
            StringBuilder query = new StringBuilder("INSERT INTO " + table.name() + " (");
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    query.append(column.name()).append(",");
                }
            }
            if (query.toString().trim().endsWith(",")) {
                query = new StringBuilder(query.substring(0, query.length() - 1));
            }
            query.append(") VALUES (");
            for (Field field : fields) {
                if (field.getType().getSimpleName().endsWith("String")) {
                    query.append(",'").append(field.get(object)).append("',");
                } else {
                    query.append(field.get(object));
                }
            }
            if (query.toString().trim().endsWith(",")) {
                query = new StringBuilder(query.substring(0, query.length() - 1));
            }
            query.append(")");
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
