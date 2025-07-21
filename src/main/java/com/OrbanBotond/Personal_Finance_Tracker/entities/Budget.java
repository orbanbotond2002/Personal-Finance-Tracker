package com.OrbanBotond.Personal_Finance_Tracker.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.YearMonth;

@Getter
@Setter
@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double budget;
    private int year;
    private int month;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
