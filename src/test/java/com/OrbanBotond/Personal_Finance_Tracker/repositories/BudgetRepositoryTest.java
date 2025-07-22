package com.OrbanBotond.Personal_Finance_Tracker.repositories;

import com.OrbanBotond.Personal_Finance_Tracker.entities.Budget;
import com.OrbanBotond.Personal_Finance_Tracker.entities.Category;
import com.OrbanBotond.Personal_Finance_Tracker.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BudgetRepositoryTest {

    @Mock
    private BudgetRepository budgetRepository;

    @Test
    public void whenFindByUser_thenReturnBudgets() {
        User user = new User();
        user.setName("Budget User");
        user.setEmail("budgetuser@example.com");

        Category category = new Category();
        category.setName("Food");

        Budget budget = new Budget();
        budget.setUser(user);
        budget.setCategory(category);
        budget.setBudget(500);
        budget.setYear(2025);
        budget.setMonth(7);

        when(budgetRepository.findByUser(user)).thenReturn(List.of(budget));

        List<Budget> budgets = budgetRepository.findByUser(user);

        assertThat(budgets).hasSize(1);
        assertThat(budgets.get(0).getBudget()).isEqualTo(500);
    }

    @Test
    public void whenFindByUserNoBudgets_thenReturnEmpty() {
        User user = new User();
        user.setName("No Budget User");
        user.setEmail("nobudget@example.com");

        when(budgetRepository.findByUser(user)).thenReturn(Collections.emptyList());

        List<Budget> budgets = budgetRepository.findByUser(user);

        assertThat(budgets).isEmpty();
    }
}
