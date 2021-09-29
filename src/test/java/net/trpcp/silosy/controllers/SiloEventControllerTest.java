package net.trpcp.silosy.controllers;

import net.trpcp.silosy.model.*;
import net.trpcp.silosy.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SiloEventControllerTest {

    SiloEventController siloEventController;

    @Mock
    SiloEventService siloEventService;
    @Mock
    PersonService personService;
    @Mock
    EventKindService eventKindService;
    @Mock
    SiloService siloService;
    @Mock
    WareService wareService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        siloEventController = new SiloEventController(personService,wareService,siloEventService,siloService, eventKindService);
        mockMvc = MockMvcBuilders.standaloneSetup(siloEventController).build();
    }

    @Test
    void getFirst() {
    }

    @Test
    void getEvents() {
    }

    @Test
    void saveEvent() {
    }

    @Test
    public void testSiloEventPersistFail() throws Exception{
        SiloEvent se = new SiloEvent();
        se.setId(1L);
        Set<SiloEvent> sev = new HashSet<>();
        sev.add(SiloEvent.builder().id(2L).build());
        sev.add(SiloEvent.builder().id(3L).build());
        Set<Ware> wares = new HashSet<>();
        wares.add(Ware.builder().id(4L).build());
        wares.add(Ware.builder().id(5L).build());
        Set<Person> persons = new HashSet<>();
        persons.add(Person.builder().id(6L).build());
        persons.add(Person.builder().id(7L).build());
        List<EventKind> eks = new ArrayList<>();
        eks.add(EventKind.builder().id(8L).build());
        eks.add(EventKind.builder().id(9L).build());

        when(siloEventService.findById(anyLong())).thenReturn(se);
        when(siloEventService.findBySilo(any())).thenReturn(sev);
        when(wareService.findAll()).thenReturn(wares);
        when(personService.findAll()).thenReturn(persons);
        when(siloEventService.findBySiloOrderByEventTimeDesc(any())).thenReturn(sev);
        when(eventKindService.findForEmpty()).thenReturn(eks);
        when(siloService.findById(anyLong())).thenReturn(Optional.of(Silo.builder().id(10L).ware(Ware.builder().id(12L).name("przenica").build()).stored(0f).build()));
        when(eventKindService.findById(anyLong())).thenReturn(EventKind.builder().id(11L).build());


//        mockMvc.perform(post("/siloevent/1")
//                .contentType((MediaType.APPLICATION_FORM_URLENCODED))
//                .param("quantity","14,6")
//                )
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("silo"))
//                .andExpect(view().name("siloevent"));

    }
}