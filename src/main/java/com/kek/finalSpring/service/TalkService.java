package com.kek.finalSpring.service;

import com.kek.finalSpring.entity.Conference;
import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.entity.Talk;
import com.kek.finalSpring.repository.ConferenceRepo;
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

    @Transactional
    public void showAllTalks(Model model) {
        List<Talk> talks = (List<Talk>) talkRepo.findAll();
        model.addAttribute("talks", talks);
    }

    @Transactional
    public void addTalk(String name, String time, String conference_id, Model model) {
        Conference conference = conferenceRepo.findById(Long.parseLong(conference_id)).orElse(null);

        if (conference!=null) {

            int hours = Integer.parseInt(time.split(":")[0]);
            int minutes = Integer.parseInt(time.split(":")[1]);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(conference.getDate());
            calendar.add(Calendar.HOUR_OF_DAY, hours);
            calendar.add(Calendar.MINUTE, minutes);
            Date date = calendar.getTime();

            Talk talk = new Talk(name, date);
            talk.getConferences().add(conference);
            talkRepo.save(talk);

            model.addAttribute("newTalk", talk);

            conference.getTalks().add(talk);
            conferenceRepo.save(conference);

//            List<Participant> speakers = participantService.findAll().stream()
//                    .filter(Participant::isSpeaker)
//                    .collect(Collectors.toList());

            conferenceService.showAllConferences(model,"");
            showAllTalks(model);
        }
    }
}
