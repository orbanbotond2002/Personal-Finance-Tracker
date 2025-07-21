package com.OrbanBotond.Personal_Finance_Tracker.controllers;

import com.OrbanBotond.Personal_Finance_Tracker.entities.MyModel;
import com.OrbanBotond.Personal_Finance_Tracker.repositories.MyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class myController {
    private final MyRepository myRepository;

    public myController(MyRepository myRepository) {
        this.myRepository = myRepository;
    }
    @GetMapping("/get")
    public List<MyModel> getAllMyModels() {
        return myRepository.findAll();
    }

    @PostMapping
    public MyModel addMyModel(@RequestBody MyModel myModel) {
        return myRepository.save(myModel);
    }
}
