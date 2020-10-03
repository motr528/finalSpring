package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.repository.ConferenceRepo;
import com.kek.finalSpring.repository.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class MainController {
    @Autowired
    private ConferenceRepo conferenceRepo;

    @Autowired
    private ParticipantRepo participantRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Conference> conferences = conferenceRepo.findAll();

        model.put("conferences", conferences);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String name, @RequestParam String location, Map<String, Object> model) {
        Conference conference = new Conference(name, location);

        conferenceRepo.save(conference);

        Iterable<Conference> conferences = conferenceRepo.findAll();

        model.put("conferences", conferences);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Conference> conferences;

        if (filter != null && !filter.isEmpty()) {
            conferences = conferenceRepo.findByLocation(filter);
        } else {
            conferences = conferenceRepo.findAll();
        }

        model.put("conferences", conferences);

        return "main";
    }

    @PostMapping("addParticipant")
    public String addParticipant (@AuthenticationPrincipal Participant participant, @RequestParam("id") Long id) {
        Conference conference = conferenceRepo.findById(id).orElse(null);
        participant.getConferences().add(conference);
        conferenceRepo.save(conference);
        participantRepo.save(participant);

        return "redirect:main";
    }
}