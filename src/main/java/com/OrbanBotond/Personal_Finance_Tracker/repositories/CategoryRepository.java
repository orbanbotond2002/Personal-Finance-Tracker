package com.OrbanBotond.Personal_Finance_Tracker.repositories;

import com.OrbanBotond.Personal_Finance_Tracker.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
