package com.kek.finalSpring.service;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.repository.ConferenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepo conferenceRepo;

    public void showAllConferences(Model model, String filter) {
        List<Conference> conferences = (List<Conference>) conferenceRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            conferences = conferenceRepo.findByLocation(filter);
        }

        sortByDate(conferences);

        model.addAttribute("conferences", conferences);
        model.addAttribute("filter", filter);
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
}
