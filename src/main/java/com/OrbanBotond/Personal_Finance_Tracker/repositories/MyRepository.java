package com.OrbanBotond.Personal_Finance_Tracker.repositories;

import com.OrbanBotond.Personal_Finance_Tracker.entities.MyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRepository extends JpaRepository<MyModel, Long> {
}
