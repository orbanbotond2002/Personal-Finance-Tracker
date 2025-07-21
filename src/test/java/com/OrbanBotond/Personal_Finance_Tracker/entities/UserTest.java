package com.OrbanBotond.Personal_Finance_Tracker.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
        User user = new User();

        user.setId(123L);
        user.setName("Botond");
        user.setEmail("botond@example.com");

        assertEquals(123L, user.getId());
        assertEquals("Botond", user.getName());
        assertEquals("botond@example.com", user.getEmail());
    }
}
