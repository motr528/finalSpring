package com.kek.finalSpring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "talk")
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.TIME)
    private Date time;

    public Talk(String name, Date time) {
        this.name = name;
        this.time = time;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker_id")
    private Participant speaker;

    @ManyToMany(mappedBy = "talks", fetch = FetchType.EAGER)
    private Set<Conference> conferences = new HashSet<>();

    @ManyToMany(mappedBy = "possibleTalks", fetch = FetchType.EAGER)
    private Set<Participant> possibleSpeaker = new HashSet<>();

    public boolean hasSpeaker() {
        return speaker == null;
    }

    public boolean hasPotentialSpeaker() {
        return !possibleSpeaker.isEmpty();
    }

    public int getSize() {
        return possibleSpeaker.size();
    }

}
