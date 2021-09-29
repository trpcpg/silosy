package net.trpcp.silosy.services;

import net.trpcp.silosy.exceptions.NotFoundException1;
import net.trpcp.silosy.model.Silo;
import net.trpcp.silosy.repositories.SiloRepository;
import org.hibernate.jdbc.Expectations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class SiloEventServiceImplTest {

    @Mock
    SiloRepository siloRepository;

    SiloServiceImpl siloServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        siloServiceImpl = new SiloServiceImpl(siloRepository);
    }

    @Test
    public void getSiloByIdTestNotFound() throws Exception{
        Optional<Silo> siloOptional = Optional.empty();
        when(siloRepository.findById(anyLong())).thenReturn(siloOptional);
        Silo returnedSilo = siloServiceImpl.findById(1L).orElse(null);
        assertEquals(returnedSilo.getId(),1L);
    }

    @Test
    void findById() {
    }

    @Test
    void findByEventTime() {
    }

    @Test
    void findBySilo() {
    }

    @Test
    void findBySiloName() {
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