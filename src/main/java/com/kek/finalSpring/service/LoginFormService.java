package com.kek.finalSpring.service;

import com.kek.finalSpring.dto.ParticipantDTO;
import org.springframework.stereotype.Service;

@Service
public class LoginFormService {
    public String inputUser(ParticipantDTO participantDTO) {
        System.out.println("Service:" + participantDTO);
        return "";
    }
}

