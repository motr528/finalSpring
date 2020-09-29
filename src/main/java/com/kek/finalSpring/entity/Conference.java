package com.kek.finalSpring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private String location;

    public Conference() {
    }

    public Conference(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void setName(String text) {
        this.name = text;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String tag) {
        this.location = tag;
    }
}

