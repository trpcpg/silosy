package net.trpcp.silosy.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SiloEventTest {

    SiloEvent se;
    Long id = 1L;
    LocalDateTime now = LocalDateTime.now();
    Silo silo = Silo.builder().id(4L).name("piatka").capacity(13000f).build();
    Ware ware = Ware.builder().id(3L).name("kukurydza").build();
    Float quantity = 123f;
    String document = "doc number";
    String description = "some description";
    Person person = Person.builder().id(2L).firstName("Tom").lastName("Bram").build();


    @BeforeEach
    void setUp() {
        se = SiloEvent.builder().id(id).eventTime(now).silo(silo).ware(ware).quantity(quantity).document(document).description(description).person(person).build();
    }

    @Test
    void builder() {
        assertEquals(se.getId(),id);
        assertEquals(se.getDescription(),description);
        assertEquals(se.getPerson().getLastName(), "Bram");
        assertEquals(se.getSilo().getName(),"piatka");
    }
}