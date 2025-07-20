package com.OrbanBotond.Personal_Finance_Tracker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myController {
    @GetMapping("/ping")
    public String pint(){
        return "pong";
    }
}
