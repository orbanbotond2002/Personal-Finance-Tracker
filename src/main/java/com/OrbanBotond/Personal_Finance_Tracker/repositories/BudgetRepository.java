package com.OrbanBotond.Personal_Finance_Tracker.repositories;

import com.OrbanBotond.Personal_Finance_Tracker.entities.Budget;
import com.OrbanBotond.Personal_Finance_Tracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUser(User user);
}