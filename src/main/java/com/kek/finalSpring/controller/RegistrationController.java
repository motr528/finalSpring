package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.entity.ParticipantDetails;
import com.kek.finalSpring.entity.Role;
import com.kek.finalSpring.repository.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {
    @Autowired
    private ParticipantRepo participantRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("roles", Role.values());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Participant participant, ParticipantDetails details, @RequestParam("role") String role) {
        Participant participantFromDb = participantRepo.findByEmail(participant.getEmail());

        if (participantFromDb != null) {
//            model.put("message", "User exists!");
            return "error";
        }

        participant.setRoles(Collections.singleton(Role.valueOf(role.toUpperCase())));

        participant.setDetails(details);
        details.setParticipant(participant);
        participantRepo.save(participant);

        return "redirect:/login";
    }
}
