package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.repository.ConferenceRepo;
import com.kek.finalSpring.repository.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConferencesController {

    @Autowired
    private ConferenceRepo conferenceRepo;
    @Autowired
    private ParticipantRepo participantRepo;

    @GetMapping("/conferences")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Conference> conferences = conferenceRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            conferences = conferenceRepo.findByLocation(filter);
        }

        model.addAttribute("conferences", conferences);
        model.addAttribute("filter", filter);

        return "conferences";
    }

    @PostMapping("addParticipant")
    public String addParticipant (@AuthenticationPrincipal Participant participant, @RequestParam("id") Long id) {

        Conference conference = conferenceRepo.findById(id).orElse(null);
//        Participant participant = participantRepo.findByEmail(participantDTO.getEmail());
        participant.getConferences().add(conference);
//        conferenceRepo.save(conference);
        participantRepo.save(participant);

        return "redirect:conferences";
    }
}
