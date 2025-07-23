package com.OrbanBotond.Personal_Finance_Tracker.mappers;

import com.OrbanBotond.Personal_Finance_Tracker.dto.BudgetRequestDTO;
import com.OrbanBotond.Personal_Finance_Tracker.dto.BudgetResponseDTO;
import com.OrbanBotond.Personal_Finance_Tracker.entities.Budget;
import com.OrbanBotond.Personal_Finance_Tracker.entities.Category;
import com.OrbanBotond.Personal_Finance_Tracker.entities.User;

public class BudgetMapper {

    public static Budget toEntity(Budget budget, BudgetRequestDTO dto, User user, Category category) {
        budget.setBudget(dto.getBudget());
        budget.setYear(dto.getYear());
        budget.setMonth(dto.getMonth());
        budget.setUser(user);
        budget.setCategory(category);
        return budget;
    }

    public static Budget newBudget(BudgetRequestDTO dto, User user, Category category) {
        Budget budget = new Budget();
        return toEntity(budget, dto, user, category);
    }

    public static BudgetResponseDTO toDTO(Budget budget) {
        BudgetResponseDTO dto = new BudgetResponseDTO();
        dto.setId(budget.getId());
        dto.setBudget(budget.getBudget());
        dto.setYear(budget.getYear());
        dto.setMonth(budget.getMonth());
        dto.setCategoryName(budget.getCategory().getName());
        dto.setUserEmail(budget.getUser().getEmail());
        return dto;
    }
}
