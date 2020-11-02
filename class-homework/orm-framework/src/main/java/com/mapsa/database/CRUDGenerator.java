package com.mapsa.database;

import com.mapsa.persistence.Column;
import com.mapsa.persistence.Table;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author esmaeil
 * @date 01.11.20
 */
public class CRUDGenerator {

    public void create(Object object) throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = null;
        try {
            connection = dbConnection.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    public void save(Object object) throws IOException, IllegalAccessException, SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();
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
                query.append("'").append(field.get(object)).append("',");
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
    }
}
