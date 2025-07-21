package com.OrbanBotond.Personal_Finance_Tracker.entities;

import com.OrbanBotond.Personal_Finance_Tracker.enums.TransactionType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TransactionTest {

    @Test
    public void testGettersAndSetters() {
        Transaction transaction = new Transaction();

        transaction.setId(1L);
        transaction.setDescription("Grocery shopping");
        transaction.setAmount(new BigDecimal("5000"));
        transaction.setDate(LocalDate.of(2025, 7, 21));
        transaction.setType(TransactionType.EXPENSE);

        User user = new User();
        user.setId(2L);
        user.setName("Botond");
        user.setEmail("botond@example.com");

        Category category = new Category();
        category.setId(3L);
        category.setName("Food");

        transaction.setUser(user);
        transaction.setCategory(category);

        assertThat(transaction.getId()).isEqualTo(1L);
        assertThat(transaction.getDescription()).isEqualTo("Grocery shopping");
        assertThat(transaction.getAmount()).isEqualByComparingTo(new BigDecimal("5000"));
        assertThat(transaction.getDate()).isEqualTo(LocalDate.of(2025, 7, 21));
        assertThat(transaction.getType()).isEqualTo(TransactionType.EXPENSE);
        assertThat(transaction.getUser()).isEqualTo(user);
        assertThat(transaction.getCategory()).isEqualTo(category);
    }
}
