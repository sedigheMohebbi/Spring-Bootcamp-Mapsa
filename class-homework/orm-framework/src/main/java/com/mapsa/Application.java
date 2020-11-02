package com.mapsa;

import com.mapsa.database.CRUDGenerator;
import com.mapsa.model.Person;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author esmaeil
 * @date 01.11.20
 */
public class Application {
    public static void main(String[] args) throws Exception {
        CRUDGenerator generator = new CRUDGenerator();
        generator.create(new Person());
        // after generator table
//        Person person = new Person();
//        person.setId(1);
//        generator.save(person);
    }
}
