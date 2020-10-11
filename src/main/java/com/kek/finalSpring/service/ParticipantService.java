package com.kek.finalSpring.service;

import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.entity.Role;
import com.kek.finalSpring.repository.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParticipantService implements UserDetailsService {
    @Autowired
    private ParticipantRepo participantRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return participantRepo.findByEmail(email);
    }

    public List<Participant> findAll() {
        return participantRepo.findAll();
    }


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

    public void updateProfile(Participant user, String firstName, String lastName, String password) {

        user.getDetails().setFirstName(firstName);
        user.getDetails().setLastName(lastName);


        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }

        participantRepo.save(user);
    }
}

