package com.kek.finalSpring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "conference")
public class Conference {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private String location;

    @ManyToMany(mappedBy = "conferences", fetch = FetchType.EAGER)
//    private Set<Participant> participants = new HashSet<>();
    private List<Participant> participants = new ArrayList<>();

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

