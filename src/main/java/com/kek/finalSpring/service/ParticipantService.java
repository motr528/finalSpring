package com.kek.finalSpring.service;

import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.entity.Role;
import com.kek.finalSpring.entity.Talk;
import com.kek.finalSpring.repository.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParticipantService implements UserDetailsService {
    @Autowired
    private ParticipantRepo participantRepo;
    @Autowired
    private TalkService talkService;

    @Transactional
    public Participant findById(Long id) {
        return participantRepo.findById(id).orElse(new Participant());
    }

    @Transactional
    public Participant findByEmail(String email) {
        return participantRepo.findByEmail(email);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return participantRepo.findByEmail(email);
    }

    public List<Participant> findAll() {
        return participantRepo.findAll();
    }

    @Transactional
    public void saveUser(Participant user, String email, Map<String, String> form) {
        user.setEmail(email);
        user.getDetails().setFirstName(form.get("firstName"));
        user.getDetails().setLastName(form.get("lastName"));


        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        participantRepo.save(user);
    }

    @Transactional
    public void updateProfile(Participant user, String firstName, String lastName, String password) {

        user.getDetails().setFirstName(firstName);
        user.getDetails().setLastName(lastName);


        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        participantRepo.save(user);
    }

    @Transactional
    public void findSpeakers(Model model) {
        List<Participant> speakers = findAll().stream()
                .filter(Participant::isSpeaker)
                .collect(Collectors.toList());

        model.addAttribute("speakers", speakers);
    }

    @Transactional
    public void assignToTalkAndShowIt(String talkId, String speakerId, Model model) {

        Talk talk = talkService.findById(Long.parseLong(talkId));
        Participant possibleSpeaker = findById(Long.parseLong(speakerId));

        talk.getPossibleSpeaker().add(possibleSpeaker);
        talkService.save(talk);

        possibleSpeaker.getPossibleTalks().add(talk);
        participantRepo.save(possibleSpeaker);

        List<Talk> talks = talkService.findAll().stream()
                .filter(Talk::hasSpeaker)
                .filter(t -> possibleSpeaker.getPossibleTalks().contains(t))
                .collect(Collectors.toList());

        model.addAttribute("talksAssigned", talks);
        talkService.showTalksWithoutAssignedToSpeaker(possibleSpeaker.getEmail(),model);
        model.addAttribute("noButton", "noButton");
    }

}

