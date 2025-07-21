package com.OrbanBotond.Personal_Finance_Tracker.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BudgetTest {

    @Test
    void testBudgetEntitySettersAndGetters() {
        Budget budget = new Budget();
        budget.setId(1L);
        budget.setBudget(5000.0);
        budget.setYear(2025);
        budget.setMonth(7);

        Category category = new Category();
        category.setId(2L);
        category.setName("Food");

        User user = new User();
        user.setId(3L);
        user.setName("Botond");
        user.setEmail("botond@example.com");

        budget.setCategory(category);
        budget.setUser(user);

        assertThat(budget.getId()).isEqualTo(1L);
        assertThat(budget.getBudget()).isEqualTo(5000.0);
        assertThat(budget.getYear()).isEqualTo(2025);
        assertThat(budget.getMonth()).isEqualTo(7);
        assertThat(budget.getCategory()).isEqualTo(category);
        assertThat(budget.getUser()).isEqualTo(user);
    }
}
