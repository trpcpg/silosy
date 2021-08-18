package net.trpcp.silosy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SiloTest {

    Silo silo;
    Integer CAPACITY = 13000;
    Long ID = 1L;
    String NAME = "prawy-górny";

    @BeforeEach
    void setUp() {
        silo = Silo.builder().id(ID).name(NAME).capacity(CAPACITY).build();
    }

    @Test
    void builder() {
        assertEquals(silo.getCapacity(),CAPACITY);
        assertEquals(silo.getId(),ID);
        assertEquals(silo.getName(),NAME);
    }
}