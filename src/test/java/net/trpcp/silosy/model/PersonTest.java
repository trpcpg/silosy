package net.trpcp.silosy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person person;
    String FIRST_NAME = "Tom";
    String LAST_NAME = "Bram";
    Long id = 1L;

    @BeforeEach
    void setUp() {
        person = Person.builder().id(id).firstName(FIRST_NAME).lastName(LAST_NAME).build();
    }

    @Test
    void builder() {
        assertEquals(person.getId(),id);
        assertEquals(person.getFirstName(),FIRST_NAME);
        assertEquals(person.getLastName(),LAST_NAME);
    }
}