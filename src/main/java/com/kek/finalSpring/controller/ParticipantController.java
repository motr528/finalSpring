package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.entity.Role;
import com.kek.finalSpring.repository.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class ParticipantController {

    @Autowired
    private ParticipantRepo participantRepo;

    @GetMapping
    public String participantList(Model model) {
        model.addAttribute("participants", participantRepo.findAll());
        return "participantList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable Participant user, Model model) {
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String email,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") Participant user) {

        user.setEmail(email);
        user.getDetails().setFirstName(form.get("firstName"));
        user.getDetails().setLastName(form.get("lastName"));


        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        participantRepo.save(user);

        return "redirect:/user";
    }

}
