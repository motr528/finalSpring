package com.kek.finalSpring.service;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.entity.Talk;
import com.kek.finalSpring.repository.ConferenceRepo;
import com.kek.finalSpring.repository.ParticipantRepo;
import com.kek.finalSpring.repository.TalkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TalkService {

    @Autowired
    private TalkRepo talkRepo;
    @Autowired
    private ConferenceRepo conferenceRepo;
    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private ParticipantRepo participantRepo;

    @Transactional
    public void showAllTalks(Model model) {
        List<Talk> talks = (List<Talk>) talkRepo.findAll();
        model.addAttribute("talks", talks);
    }

    @Transactional
    public void addTalk(String name, String time, String conferenceId, String speakerId, Model model) {
        boolean speakerIsFree = true;

        Conference conference = conferenceRepo.findById(Long.parseLong(conferenceId)).orElse(null);
        Participant speaker = participantRepo.findById(Long.parseLong(speakerId)).orElse(null);

        if (conference == null || speaker == null) {
            throw new RuntimeException();
        }

        for (Talk talk : speaker.getTalks()) {
            if (talk.getName().equals(name) && talk.getConferences().contains(conference)) {
                speakerIsFree = false;
                break;
            }
        }

        if (speakerIsFree) {
            Date date = setTimeForTalk(time, conference.getDate());

            Talk talk = new Talk(name, date);
            talk.getConferences().add(conference);
            talkRepo.save(talk);

            model.addAttribute("newTalk", talk);

            conference.getTalks().add(talk);
            conferenceRepo.save(conference);

            conferenceService.showAllConferences(model, "");

            showAllTalks(model);
        }

    }

    private Date setTimeForTalk(String time, Date conferenceDate) {

        int hours = Integer.parseInt(time.split(":")[0]);
        int minutes = Integer.parseInt(time.split(":")[1]);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(conferenceDate);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}
