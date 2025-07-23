package com.OrbanBotond.Personal_Finance_Tracker.mappers;

import com.OrbanBotond.Personal_Finance_Tracker.dto.*;
import com.OrbanBotond.Personal_Finance_Tracker.entities.Budget;
import com.OrbanBotond.Personal_Finance_Tracker.entities.Category;
import com.OrbanBotond.Personal_Finance_Tracker.entities.Transaction;
import com.OrbanBotond.Personal_Finance_Tracker.entities.User;
import com.OrbanBotond.Personal_Finance_Tracker.enums.TransactionType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MapperTests {

    @Test
    void testUserMapper() {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setName("John");
        dto.setEmail("john@example.com");

        User user = UserMapper.newUser(dto);

        assertThat(user.getName()).isEqualTo("John");
        assertThat(user.getEmail()).isEqualTo("john@example.com");

        UserResponseDTO response = UserMapper.toDTO(user);
        assertThat(response.getName()).isEqualTo("John");
        assertThat(response.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void testCategoryMapper() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("Utilities");

        Category category = CategoryMapper.newCategory(dto);

        assertThat(category.getName()).isEqualTo("Utilities");

        CategoryResponseDTO response = CategoryMapper.toDTO(category);
        assertThat(response.getName()).isEqualTo("Utilities");
    }

    @Test
    void testBudgetMapper() {
        BudgetRequestDTO dto = new BudgetRequestDTO();
        dto.setBudget(1000);
        dto.setYear(2025);
        dto.setMonth(7);

        User user = new User();
        user.setEmail("user@example.com");

        Category category = new Category();
        category.setName("Groceries");

        Budget budget = BudgetMapper.newBudget(dto, user, category);

        assertThat(budget.getBudget()).isEqualTo(1000);
        assertThat(budget.getYear()).isEqualTo(2025);
        assertThat(budget.getMonth()).isEqualTo(7);

        BudgetResponseDTO response = BudgetMapper.toDTO(budget);
        assertThat(response.getBudget()).isEqualTo(1000);
        assertThat(response.getYear()).isEqualTo(2025);
        assertThat(response.getMonth()).isEqualTo(7);
        assertThat(response.getUserEmail()).isEqualTo("user@example.com");
        assertThat(response.getCategoryName()).isEqualTo("Groceries");
    }

    @Test
    void testTransactionMapper() {
        TransactionRequestDTO dto = new TransactionRequestDTO();
        dto.setDescription("Salary");
        dto.setAmount(BigDecimal.valueOf(3000));
        dto.setDate(LocalDate.of(2025, 7, 23));
        dto.setType(TransactionType.INCOME);

        User user = new User();
        user.setEmail("john@example.com");

        Category category = new Category();
        category.setName("Income");

        Transaction transaction = TransactionMapper.newTransaction(dto, user, category);

        assertThat(transaction.getDescription()).isEqualTo("Salary");
        assertThat(transaction.getAmount()).isEqualByComparingTo("3000");
        assertThat(transaction.getType()).isEqualTo(TransactionType.INCOME);

        TransactionResponseDTO response = TransactionMapper.toDTO(transaction);
        assertThat(response.getDescription()).isEqualTo("Salary");
        assertThat(response.getAmount()).isEqualByComparingTo("3000");
        assertThat(response.getType()).isEqualTo("INCOME");
        assertThat(response.getUserEmail()).isEqualTo("john@example.com");
        assertThat(response.getCategoryName()).isEqualTo("Income");
    }
}
