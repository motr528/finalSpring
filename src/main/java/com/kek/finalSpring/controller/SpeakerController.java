package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.repository.ParticipantRepo;
import com.kek.finalSpring.repository.TalkRepo;
import com.kek.finalSpring.service.ConferenceService;
import com.kek.finalSpring.service.ParticipantService;
import com.kek.finalSpring.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Part;

@Controller
public class SpeakerController {

    @Autowired
    private TalkService talkService;
    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private TalkRepo talkRepo;
    @Autowired
    private ParticipantRepo participantRepo;
    @Autowired
    private ParticipantService participantService;


    @GetMapping("/talks")
    public String showTalks(@AuthenticationPrincipal Participant participant, Model model) {

        talkService.showTalksWithoutAssignedToSpeaker(participant.getEmail(),model);
        talkService.showTalksAssignedToSpeaker(participant.getEmail(), model);

        return "talks";
    }



    @PostMapping("/assignToTalk")
    public String addTalk(
            @RequestParam String speakerId,
            @RequestParam String talkId,
            Model model) {

        participantService.assignToTalkAndShowIt(talkId, speakerId, model);

        return "talks";
    }

    @GetMapping("proposeTalk")
    public String showProposedTalks(@AuthenticationPrincipal Participant participant, Model model) {
        talkService.showSpeakersProposedTalks(participant.getEmail(), model);
        conferenceService.showAllConferencesWithSlots(model);
        return "proposeTalk";
    }

    @PostMapping("/proposeTalk")
    public String createTalk(@RequestParam String name,
                             @RequestParam String time,
                             @RequestParam String conferenceId,
                             @RequestParam String talkId,
                             @RequestParam String speakerId, Model model) {


        return "proposeTalk";

    }
}
