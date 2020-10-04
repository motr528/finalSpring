package com.kek.finalSpring.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ParticipantDetails {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Participant participant;

    @Id
    private Long id;

    private String firstName;
    private String lastName;

}
