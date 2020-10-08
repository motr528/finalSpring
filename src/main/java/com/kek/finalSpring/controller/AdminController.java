package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.repository.ConferenceRepo;
import com.kek.finalSpring.repository.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private ConferenceRepo conferenceRepo;

    @GetMapping("/addConference")
    public String main(Model model) {
        Iterable<Conference> conferences = conferenceRepo.findAll();

        model.addAttribute("conferences", conferences);

        return "addConference";
    }

    @PostMapping("/addConference")
    public String add(@RequestParam String name, @RequestParam String location, Map<String, Object> model) {
        Conference conference = new Conference(name, location);

        conferenceRepo.save(conference);

        Iterable<Conference> conferences = conferenceRepo.findAll();

        model.put("conferences", conferences);

        return "addConference";
    }
}
