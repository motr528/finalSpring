package com.kek.finalSpring.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "participant_details")
public class ParticipantDetails {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Participant participant;

    @Id
    private Long id;

    private String firstName;
    private String lastName;

}
