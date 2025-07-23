package com.OrbanBotond.Personal_Finance_Tracker.dto;

import com.OrbanBotond.Personal_Finance_Tracker.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequestDTO {
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private TransactionType type;
    private Long categoryId;
    private Long userId;
}
