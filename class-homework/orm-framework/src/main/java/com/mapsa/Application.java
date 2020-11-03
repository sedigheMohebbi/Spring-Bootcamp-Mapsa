package com.mapsa;

import com.mapsa.database.CRUDGenerator;
import com.mapsa.model.Teacher;

/**
 * @author esmaeil
 * @date 01.11.20
 */
public class Application {
    public static void main(String[] args) throws Exception {
        CRUDGenerator generator = new CRUDGenerator();
//        generator.create(new Person());

        // after generator table
//        Person person = new Person();
//        person.setId(1);
//        generator.save(person);
//        generator.load(person);
//        generator.create(new Teacher());
        Teacher teacher2 = new Teacher();
        teacher2.setId(5);
        teacher2.setName("MAMAd");
//        generator.save(teacher2);
        generator.load(teacher2);
        teacher2.setName("PASHMAK");
        generator.update(teacher2);
//        generator.save(teacher);
//        Object o = generator.load(teacher);
//        System.out.println(o.toString());
//        generator.delete(teacher);

    }
}
