package com.kek.finalSpring.controller;

import com.kek.finalSpring.dto.ParticipantDTO;
import com.kek.finalSpring.service.RegFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class RegFormController {

//    private final RegFormService regFormService;

//    @Autowired
    public RegFormController(RegFormService regFormService) {
//        this.regFormService = regFormService;
//        System.out.println("kek");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/reg_form", method = RequestMethod.POST)
    public void registrationFormController(ParticipantDTO participantDTO) {
        log.info("{}", participantDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
