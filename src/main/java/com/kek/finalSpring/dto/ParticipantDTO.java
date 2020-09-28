package com.kek.finalSpring.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ParticipantDTO {

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String role;

}

