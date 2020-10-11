package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.repository.ConferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public String addConference(@RequestParam String name, @RequestParam String location, @RequestParam String date, Model model) {
        Date dateFromString;

        try {
            dateFromString = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            if (dateFromString.getTime() < new Date().getTime()) {
                throw new RuntimeException("this date has passed");
            }
        } catch (ParseException | RuntimeException e) {
            return "error";
        }
        Conference conference = new Conference(name, location, dateFromString);

        conferenceRepo.save(conference);

        model.addAttribute("newConf", conference);

        Iterable<Conference> conferences = conferenceRepo.findAll();

        model.addAttribute("conferences", conferences);

        return "addConference";
    }
}
