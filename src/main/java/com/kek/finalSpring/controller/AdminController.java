package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.entity.Talk;
import com.kek.finalSpring.repository.ConferenceRepo;
import com.kek.finalSpring.repository.TalkRepo;
import com.kek.finalSpring.service.ConferenceService;
import com.kek.finalSpring.service.ParticipantService;
import com.kek.finalSpring.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class AdminController {
    @Autowired
    private ConferenceRepo conferenceRepo;
    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private TalkService talkService;
    @Autowired
    private TalkRepo talkRepo;
    @Autowired
    private ParticipantService participantService;

    @GetMapping("/addConference")
    public String main(Model model) {
        Iterable<Conference> conferences = conferenceRepo.findAll();

        model.addAttribute("conferences", conferences);

        return "addConference";
    }

    @PostMapping("/addConference")
    public String addConference(@RequestParam String name, @RequestParam String location, @RequestParam String date,
                                @RequestParam Integer numOfSlots ,Model model) {
        Date dateFromString;

        try {
            dateFromString = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            if (dateFromString.getTime() < new Date().getTime()) {
                throw new RuntimeException("this date has passed");
            }
        } catch (ParseException | RuntimeException e) {
            return "error";
        }
        Conference conference = new Conference(name, location, dateFromString, numOfSlots);

        conferenceRepo.save(conference);

        model.addAttribute("newConf", conference);

        Iterable<Conference> conferences = conferenceRepo.findAll();

        model.addAttribute("conferences", conferences);

        return "addConference";
    }

    @GetMapping("/addTalk")
    public String showTalks(Model model) {

        participantService.findSpeakers(model);
        conferenceService.showAllConferences(model,"");
        talkService.showAllTalks(model);

        return "addTalk";
    }

    @PostMapping("/addTalk")
    public String addTalk(@RequestParam String name, @RequestParam String time, @RequestParam String conference_id, @RequestParam String speaker_id, Model model) {

        talkService.addTalk(name, time, conference_id, speaker_id, model);

        return "addTalk";
    }

}
