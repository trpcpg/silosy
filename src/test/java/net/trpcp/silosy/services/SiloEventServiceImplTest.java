package net.trpcp.silosy.services;

import net.trpcp.silosy.model.*;
import net.trpcp.silosy.repositories.SiloEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class SiloEventServiceImplTest {

    @Mock
    SiloEventRepository siloEventRepository;
    @Mock
    SiloService siloService;
    @Mock
    PersonService personService;
    @Mock
    WareService wareService;

    SiloEventService siloEventService;

    Long ID = 1L;
    SiloEvent se1;
    SiloEvent se2;
    Set<SiloEvent> ses;
    LocalDateTime eventTime = LocalDateTime.now();
    Set<Silo> siloSet = new HashSet<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        siloEventService = new SiloEventServiceImpl(siloEventRepository,siloService, personService, wareService);
        se1 = SiloEvent.builder()
                .id(ID)
                .eventTime(eventTime)
                .eventKind(EventKind.builder()
                        .id(2L)
                        .build()
                )
                .person(Person.builder()
                        .firstName("Tom")
                        .build()
                )
                .ware(Ware.builder()
                        .name("przenica")
                        .build()
                )
                .quantity(123f)
                .document("doc")
                .description("desc")
                .silo(Silo.builder()
                        .id(3L)
                        .name("Silo1")
                        .capacity(12000f)
                        .build()
                )
                .build();
        se2 = SiloEvent.builder()
                .id(ID)
                .eventTime(eventTime)
                .eventKind(EventKind.builder()
                        .id(4L)
                        .build()
                )
                .person(Person.builder()
                        .firstName("Wol")
                        .build()
                )
                .ware(Ware.builder()
                        .name("kukurydza")
                        .build()
                )
                .quantity(123f)
                .document("doc")
                .description("desc")
                .silo(Silo.builder()
                        .id(5L)
                        .name("Silo2")
                        .capacity(12000f)
                        .build()
                )
                .build();
        ses = new HashSet<>(Arrays.asList(se1,se2));
        siloSet.add(Silo.builder().id(10L).name("Silo1").build());
        siloSet.add(Silo.builder().id(20L).name("Silo2").build());
    }

    @Test
    void findById() {
        when(siloEventRepository.findById(anyLong())).thenReturn(Optional.of(SiloEvent.builder().id(ID).build()));
        SiloEvent s =siloEventService.findById(ID);
        assertEquals(s.getId(),ID);
    }

    @Test
    void findByEventTime() {
        when(siloEventRepository.findByEventTime(any())).thenReturn(ses);
        Set<SiloEvent> retrieved = siloEventService.findByEventTime(LocalDateTime.now());
        assertEquals(retrieved.size(),ses.size());
    }

    @Test
    void findBySilo() {
        when(siloEventRepository.findBySilo(any())).thenReturn(ses);
        Set<SiloEvent> retrieved = siloEventService.findBySilo(siloSet.iterator().next());
        assertEquals(retrieved.size(),ses.size());
    }

    @Test
    void findBySiloName() {
        String name = "Silo3";
        Silo silo = Silo.builder().id(6L).name(name).build();       //redundant
        ses.forEach(se->se.setSilo(Silo.builder().name(name).build()));
        when(siloService.findByName(anyString())).thenReturn(silo); //redundant
        when(siloEventRepository.findBySilo(any())).thenReturn(ses);
        Set<SiloEvent> retrieved = siloEventService.findBySiloName(name);
        assertEquals(retrieved.size(),ses.size());
        assertEquals(ses.iterator().next().getSilo().getName(),name);
    }

    @Test
    void findBySilo_id() {

    }

    @Test
    void findByWare() {
    }

    @Test
    void findByPerson() {
    }

    @Test
    void findByDocument() {
    }

    @Test
    void findBySiloOrderByEventTimeDesc() {
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}