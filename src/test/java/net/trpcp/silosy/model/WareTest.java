package net.trpcp.silosy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WareTest {

    Ware ware;
    Long ID = 1L;
    String NAME = "przenica";

    @BeforeEach
    void setUp() {
        ware = Ware.builder().id(ID).name(NAME).build();
    }

    @Test
    void builder() {
        assertEquals(ware.getId(),ID);
        assertEquals(ware.getName(),NAME);
    }
}