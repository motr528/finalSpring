package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.entity.ParticipantDetails;
import com.kek.finalSpring.entity.Role;
import com.kek.finalSpring.repository.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private ParticipantRepo participantRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Participant participant, ParticipantDetails details, Map<String, Object> model) {
        Participant participantFromDb = participantRepo.findByEmail(participant.getEmail());

//        if (participantFromDb != null) {
//            model.put("message", "User exists!");
//            return "registration";
//        }

        participant.setDetails(details);
        details.setParticipant(participant);
        participant.setRoles(Collections.singleton(Role.USER));
        participantRepo.save(participant);

        return "redirect:/login";
    }
}
