package net.trpcp.silosy.controllers;

import net.trpcp.silosy.model.*;
import net.trpcp.silosy.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
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

    Silo s;
    Iterable<Silo> siloset;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        siloEventController = new SiloEventController(personService,wareService,siloEventService,siloService, eventKindService);
        mockMvc = MockMvcBuilders.standaloneSetup(siloEventController).build();
        s = Silo.builder().id(1L).name("Silo1").capacity(13000f).build();
        siloset = new HashSet<>(Arrays.asList(s,Silo.builder().id(2L).name("Silo2").capacity(13000f).build(),
                Silo.builder().id(3L).name("Silo3").capacity(13000f).build(),
                Silo.builder().id(4L).name("Silo4").capacity(13000f).build()));

    }

    @Test
    void getFirst() throws Exception{
        when(siloService.findAllOrderByName()).thenReturn(siloset);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("silos"))
                .andExpect(view().name("index"));
    }

    @Test
    void getEvents() {
    }

    @Test
    void saveEvent() {
    }

//    @Test
//    public void testSiloEventPersistFail() throws Exception{ //does not work, 'couse i'm unable to mock complex object
//        SiloEvent se = new SiloEvent();
//        se.setId(1L);
//        Ware w1 = new Ware();
//        w1.setId(12L);
//        w1.setName("przenica");
//        se.setWare(w1);
//        Silo target = new Silo();
//        target.setId(10L);
//        target.setName("targetSilo");
//        target.setCapacity(13000f);
//        Ware wt = new Ware();
//        wt.setId(12L);
//        wt.setName("przenica");
//        target.setWare(wt);
//        Set<SiloEvent> sev = new HashSet<>();
//        sev.add(SiloEvent.builder().id(2L).build());
//        sev.add(SiloEvent.builder().id(3L).build());
//        Set<Ware> wares = new HashSet<>();
//        wares.add(Ware.builder().id(4L).build());
//        wares.add(Ware.builder().id(5L).build());
//        Set<Person> persons = new HashSet<>();
//        persons.add(Person.builder().id(6L).build());
//        persons.add(Person.builder().id(7L).build());
//        List<EventKind> eks = new ArrayList<>();
//        eks.add(EventKind.builder().id(8L).build());
//        eks.add(EventKind.builder().id(9L).build());
//
//        when(siloEventService.findById(anyLong())).thenReturn(se);
//        when(siloEventService.findBySilo(any())).thenReturn(sev);
//        when(wareService.findAll()).thenReturn(wares);
//        when(personService.findAll()).thenReturn(persons);
//        when(siloEventService.findBySiloOrderByEventTimeDesc(any())).thenReturn(sev);
//        when(eventKindService.findForEmpty()).thenReturn(eks);
//        when(siloService.findById(anyLong())).thenReturn(Optional.of(target));
//        when(eventKindService.findById(anyLong())).thenReturn(EventKind.builder().id(11L).build());
//
//        mockMvc.perform(post("/siloevent/1")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("quantity","14,6")
//                        .param("targetsilo","2")
//                        .param("eventTime", "17.10.2021, 09:14")
//                        .param("ware", "1")
//                        .param("eventKind","1")
//                        .param("document", "aqqwerty")
//                        .param("description", "asddfg")
//                        .param("person","1")
//                        .flashAttr("siloevent", new SiloEvent())
//
//                )
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("silo"))
//                .andExpect(view().name("siloevent"));
//    }
}