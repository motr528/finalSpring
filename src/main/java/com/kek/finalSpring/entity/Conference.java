package com.kek.finalSpring.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Conference {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private String location;

    @ManyToMany(mappedBy = "conferences", fetch = FetchType.EAGER)
    private Set<Participant> participants = new HashSet<>();

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String tag) {
        this.location = tag;
    }

    public int getNumberOfParticipants() {
        return participants.size();
    }
}

