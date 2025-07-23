package com.OrbanBotond.Personal_Finance_Tracker.mappers;

import com.OrbanBotond.Personal_Finance_Tracker.dto.TransactionRequestDTO;
import com.OrbanBotond.Personal_Finance_Tracker.dto.TransactionResponseDTO;
import com.OrbanBotond.Personal_Finance_Tracker.entities.Category;
import com.OrbanBotond.Personal_Finance_Tracker.entities.Transaction;
import com.OrbanBotond.Personal_Finance_Tracker.entities.User;

public class TransactionMapper {

    public static Transaction newTransaction(TransactionRequestDTO dto, User user, Category category) {
        Transaction transaction = new Transaction();
        return toEntity(transaction, dto, user, category);
    }

    public static Transaction toEntity(Transaction transaction, TransactionRequestDTO dto, User user, Category category) {
        transaction.setDescription(dto.getDescription());
        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setType(dto.getType());
        transaction.setUser(user);
        transaction.setCategory(category);
        return transaction;
    }

    public static TransactionResponseDTO toDTO(Transaction transaction) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setId(transaction.getId());
        dto.setDescription(transaction.getDescription());
        dto.setAmount(transaction.getAmount());
        dto.setDate(transaction.getDate());
        dto.setType(transaction.getType().name());
        dto.setCategoryName(transaction.getCategory() != null ? transaction.getCategory().getName() : null);
        dto.setUserEmail(transaction.getUser() != null ? transaction.getUser().getEmail() : null);
        return dto;
    }
}
