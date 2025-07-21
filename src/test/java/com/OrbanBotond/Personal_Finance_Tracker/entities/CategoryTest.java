package com.OrbanBotond.Personal_Finance_Tracker.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    @Test
    public void testGettersAndSetters() {
        Category category = new Category();

        category.setId(1L);
        category.setName("Test Category");

        assertEquals(1L, category.getId());
        assertEquals("Test Category", category.getName());
    }
}
