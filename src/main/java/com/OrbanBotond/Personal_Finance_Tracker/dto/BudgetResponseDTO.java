package com.OrbanBotond.Personal_Finance_Tracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetResponseDTO {
    private Long id;
    private double budget;
    private int year;
    private int month;
    private String categoryName;
    private String userEmail;
}
