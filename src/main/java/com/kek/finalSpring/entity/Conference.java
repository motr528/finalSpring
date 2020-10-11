package com.kek.finalSpring.entity;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToMany(mappedBy = "conferences", fetch = FetchType.EAGER)
//    private Set<Participant> participants = new HashSet<>();
    private List<Participant> participants = new ArrayList<>();

    public Conference(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Conference(String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
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

    public String dateAsString() {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }


}

