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

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public String addParticipant(@AuthenticationPrincipal Participant participant, @RequestParam("id") Long id) {

        Conference conference = conferenceRepo.findById(id).orElse(null);
//        Participant participant = participantRepo.findByEmail(participantDTO.getEmail());
        participant.getConferences().add(conference);
//        conferenceRepo.save(conference);
        participantRepo.save(participant);

        return "redirect:conferences";
    }

    @PostMapping("filterByDate")
    public String filterByDate(
            @NotNull String dateFrom,
            String dateTo,
            Model model) {

        Iterable<Conference> conferences;
        Date dateFromAsDate;
        Date dateToAsDate;

        try {
            dateFromAsDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom);
            if (isDateValid(dateTo)) {
                dateToAsDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateTo);
                conferences = conferenceRepo.findByDateBetween(dateFromAsDate, dateToAsDate);
            } else {
                conferences = conferenceRepo.findByDateAfter(dateFromAsDate);
            }
        } catch (ParseException e) {
            System.out.println("wrong date");
            return "error";
        }

        model.addAttribute("conferences", conferences);

        return "conferences";
    }

    private boolean isDateValid(String date) {
        return date != null && !date.isEmpty();
    }
}
