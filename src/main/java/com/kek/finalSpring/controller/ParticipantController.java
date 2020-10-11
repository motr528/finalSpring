package com.kek.finalSpring.controller;

import com.kek.finalSpring.entity.Participant;
import com.kek.finalSpring.entity.Role;
import com.kek.finalSpring.repository.ParticipantRepo;
import com.kek.finalSpring.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")

public class ParticipantController {

    @Autowired
    private ParticipantRepo participantRepo;

    @Autowired
    private ParticipantService participantService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String participantList(Model model) {
//        model.addAttribute("participants", participantRepo.findAll());
        model.addAttribute("participants", participantService.findAll());
        return "participantList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable Participant user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String email,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") Participant user) {


        participantService.saveUser(user, email, form);

        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal Participant user) {
        model.addAttribute("firstName", user.getDetails().getFirstName());
        model.addAttribute("lastName", user.getDetails().getLastName());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal Participant user,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String password
    ) {
        participantService.updateProfile(user, firstName, lastName, password);

        return "redirect:/user/profile";
    }

}
