package com.OrbanBotond.Personal_Finance_Tracker.repositories;

import com.OrbanBotond.Personal_Finance_Tracker.entities.Transaction;
import com.OrbanBotond.Personal_Finance_Tracker.entities.User;
import com.OrbanBotond.Personal_Finance_Tracker.enums.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    public void whenFindByUser_thenReturnTransactions() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("user@example.com");

        Transaction t1 = new Transaction();
        t1.setUser(user);
        t1.setAmount(BigDecimal.valueOf(100));
        t1.setDate(LocalDate.now());
        t1.setDescription("Salary");
        t1.setType(TransactionType.INCOME);

        Transaction t2 = new Transaction();
        t2.setUser(user);
        t2.setAmount(BigDecimal.valueOf(50));
        t2.setDate(LocalDate.now());
        t2.setDescription("Groceries");
        t2.setType(TransactionType.EXPENSE);

        when(transactionRepository.findByUser(user)).thenReturn(List.of(t1, t2));

        List<Transaction> transactions = transactionRepository.findByUser(user);

        assertThat(transactions).hasSize(2);
        assertThat(transactions).extracting("description")
                .containsExactlyInAnyOrder("Salary", "Groceries");
    }

    @Test
    public void whenFindByUserWithNoTransactions_thenReturnEmptyList() {
        User user = new User();
        user.setName("Empty User");
        user.setEmail("empty@example.com");

        when(transactionRepository.findByUser(user)).thenReturn(Collections.emptyList());

        List<Transaction> transactions = transactionRepository.findByUser(user);

        assertThat(transactions).isEmpty();
    }
}
