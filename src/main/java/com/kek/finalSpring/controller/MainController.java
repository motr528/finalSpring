package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.repository.ConferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class MainController {
    @Autowired
    private ConferenceRepo conferenceRepo;

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
}