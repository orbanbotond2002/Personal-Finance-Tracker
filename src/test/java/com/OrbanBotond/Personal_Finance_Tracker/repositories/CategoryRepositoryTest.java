package com.OrbanBotond.Personal_Finance_Tracker.repositories;

import com.OrbanBotond.Personal_Finance_Tracker.entities.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void whenFindByName_thenReturnCategory() {
        Category category = new Category();
        category.setName("Utilities");

        when(categoryRepository.findByName("Utilities")).thenReturn(Optional.of(category));

        Optional<Category> found = categoryRepository.findByName("Utilities");

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Utilities");
    }

    @Test
    public void whenFindByNameNotFound_thenReturnEmpty() {
        when(categoryRepository.findByName("Nonexistent")).thenReturn(Optional.empty());

        Optional<Category> found = categoryRepository.findByName("Nonexistent");

        assertThat(found).isNotPresent();
    }
}
