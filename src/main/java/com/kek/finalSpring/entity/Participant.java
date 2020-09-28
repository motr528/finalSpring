package com.kek.finalSpring.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Participant {

    private int id;
    private String login;
    private String password;
    private String first_name;
    private String last_name;
    private int role_id;
}
