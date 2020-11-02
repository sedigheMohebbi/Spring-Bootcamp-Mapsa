package com.mapsa.model;

import com.mapsa.persistence.Column;
import com.mapsa.persistence.Table;

/**
 * @author esmaeil
 * @date 01.11.20
 */
@Table(name = "Person")
public class Person {
    @Column(name = "ID", dataType = "INTEGER")
    public int id;
    @Column(name = "NAME", dataType = "VARCHAR")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
