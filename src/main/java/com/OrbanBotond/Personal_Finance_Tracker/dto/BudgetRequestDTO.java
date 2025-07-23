package com.OrbanBotond.Personal_Finance_Tracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetRequestDTO {
    private double budget;
    private int year;
    private int month;
    private Long categoryId;
    private Long userId;
}
