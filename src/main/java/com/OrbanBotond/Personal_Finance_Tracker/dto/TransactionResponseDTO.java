package com.OrbanBotond.Personal_Finance_Tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionResponseDTO {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private String type;
    private String categoryName;
    private String userEmail;
}
