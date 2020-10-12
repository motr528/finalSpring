package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.repository.ConferenceRepo;
import com.kek.finalSpring.repository.ParticipantRepo;
import com.kek.finalSpring.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ConferencesController {

    @Autowired
    private ConferenceRepo conferenceRepo;
    @Autowired
    private ParticipantRepo participantRepo;

    @Autowired
    private ConferenceService conferenceService;

    @GetMapping("/conferences")
    public String showConferences(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        conferenceService.showAllConferences(model, filter);

        return "conferences";
    }

    @PostMapping("addParticipant")
    public String addParticipant(@AuthenticationPrincipal Participant participant, @RequestParam("id") Long id) {

        Conference conference = conferenceRepo.findById(id).orElse(null);

//        List<Long> conf_ids = new ArrayList<>();

//        conf_ids = conferenceRepo.findByParticipantId(participantRepo.findByEmail(participant.getEmail()).getId());
//        Participant participant = participantRepo.findByEmail(participantDTO.getEmail());

        participant.getConferences().add(conference);

//        conferenceRepo.save(conference);
//        participantRepo.save(participant);
//        for (Conference conf: participant.getConferences()) {
//            if (!conf_ids.contains(conference.getId())) {
//                participantRepo.insertUniqueConf(participant.getId(),conf.getId());
//            }
//        }

        participantRepo.insertUniqueConf(participant.getId(),id);

        return "redirect:conferences";
    }

    @PostMapping("filterByDate")
    public String filterByDate(
            @NotNull String dateFrom,
            String dateTo,
            Model model) {

        try {
            conferenceService.showByDate(dateFrom, dateTo, model);
        } catch (ParseException e) {
            System.out.println("wrong date");
            return "error";
        }

        return "conferences";
    }

}
