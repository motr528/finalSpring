package com.kek.finalSpring.service;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.repository.ConferenceRepo;
import com.kek.finalSpring.repository.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConferenceService {

    @Autowired
    private ParticipantRepo participantRepo;

    @Autowired
    private ConferenceRepo conferenceRepo;


    public void showAllConferences(Model model, String filter) {
        List<Conference> conferences;

        if (filter != null && !filter.isEmpty()) {
            conferences = conferenceRepo.findByLocation(filter);
        } else {
            conferences = conferenceRepo.findAll();
        }

        sortByDate(conferences);

        model.addAttribute("conferences", conferences);
        model.addAttribute("filter", filter);
    }

    public void showAllConferencesWithSlots(Model model) {
        List<Conference> conferences = conferenceRepo.findAll().stream()
                .filter(conf -> conf.getAvailableSlots() != 0)
                .collect(Collectors.toList());
        model.addAttribute("conferences", conferences);
    }


    public void showByDate(String dateFrom, String dateTo, Model model) throws ParseException {
        List<Conference> conferences;
        Date dateFromAsDate;
        Date dateToAsDate;

        dateFromAsDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom);
        if (isDateValid(dateTo)) {
            dateToAsDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateTo);
            conferences = conferenceRepo.findByDateBetween(dateFromAsDate, dateToAsDate);
        } else {
            conferences = conferenceRepo.findByDateAfter(dateFromAsDate);
        }

        sortByDate(conferences);

        model.addAttribute("conferences", conferences);
    }


    private void sortByDate(List<Conference> conferences) {
        conferences.sort(Comparator.comparing(Conference::getDate));
    }

    private boolean isDateValid(String date) {
        return date != null && !date.isEmpty();
    }

    @Transactional
    public void addParticipant(Participant participant, Long id) {
        boolean isAdded = false;

        Conference conference = conferenceRepo.findById(id).orElse(null);

        if (conferenceRepo.findConferenceIdFromJoinedTableByParticipantId(participant.getId()).contains(id)) {
            isAdded = true;
        }

        if (!isAdded) {
            participant.getConferences().add(conference);

            participantRepo.insertUniqueConf(participant.getId(), id);
        }


    }
}
