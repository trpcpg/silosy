package net.trpcp.silosy.services;

import net.trpcp.silosy.model.EventKind;
import net.trpcp.silosy.repositories.EventKindRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class EventKindServiceImplTest {

    @Mock
    EventKindRepository eventKindRepository;

    EventKindService eventKindService;

    EventKind ek1;
    EventKind ek2;
    EventKind ek3;
    EventKind ek4;
    EventKind ek5;
    EventKind ek6;
    EventKind ek7;
    EventKind ek8;
    EventKind ek9;
    Iterable<EventKind> it;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        eventKindService = new EventKindServiceImpl(eventKindRepository);
        ek1 = EventKind.builder().id(1L).name("some_name").factor(1).build();
        ek2 = EventKind.builder().id(2L).name("some_name2").factor(-1).build();
        ek3 = EventKind.builder().id(3L).name("some_name3").factor(-1).build();
        ek4 = EventKind.builder().id(4L).name("some_name4").factor(-1).build();
        ek5 = EventKind.builder().id(5L).name("some_name5").factor(-1).build();
        ek6 = EventKind.builder().id(6L).name("some_name6").factor(-1).build();
        ek7 = EventKind.builder().id(7L).name("some_name7").factor(-1).build();
        ek8 = EventKind.builder().id(8L).name("some_name8").factor(-1).build();
        ek9 = EventKind.builder().id(9L).name("some_name9").factor(-1).build();
        it = new HashSet<>(Arrays.asList(ek1,ek2,ek3,ek4,ek5,ek6,ek7,ek8,ek9));
    }

    @Test
    void findById() {

        when(eventKindRepository.findById(anyLong())).thenReturn(Optional.of(ek1));

        EventKind result = eventKindService.findById(1L);

        assertEquals(result.getId(),1L);
        assertEquals(result.getName(),"some_name");
    }

    @Test
    void notFindById(){

        when(eventKindRepository.findById(anyLong())).thenReturn(Optional.empty());

        EventKind result = eventKindService.findById(1L);

        assertNull(result);
        //assertEquals(result.getId(),1L);
        //assertEquals(result.getName(),"some_name");
    }

    @Test
    void findAll() {

        when(eventKindRepository.findAll()).thenReturn(it);

        List<EventKind> lel = eventKindService.findAll();

        assertEquals(lel.size(),9);
    }

    @Test
    void findForUsed() {
        when(eventKindRepository.findById(anyLong())).thenReturn(Optional.of(ek1));

        List<EventKind> lek = eventKindService.findForUsed();

        assertEquals(lek.size(),7);
    }

    @Test
    void findForEmpty() {
        when(eventKindRepository.findById(anyLong())).thenReturn(Optional.of(ek1));

        List<EventKind> lek = eventKindService.findForEmpty();

        assertEquals(lek.size(),3);
    }

    @Test
    void findForFull() {

        when(eventKindRepository.findById(anyLong())).thenReturn(Optional.of(ek1));

        List<EventKind> lek = eventKindService.findForFull();

        assertEquals(lek.size(),3);
    }

    @Test
    void findForGased() {

        when(eventKindRepository.findById(anyLong())).thenReturn(Optional.of(ek1));

        List<EventKind> lek = eventKindService.findForGased();

        assertEquals(lek.size(),1);
    }

    @Test
    void findForBlocked() {

        when(eventKindRepository.findById(anyLong())).thenReturn(Optional.of(ek1));

        List<EventKind> lek = eventKindService.findForBlocked();

        assertEquals(lek.size(),1);
    }

    @Test
    void findAllByIdLike() {

        when(eventKindRepository.findAllByIdLike(anyLong())).thenReturn(new HashSet<>(Arrays.asList(ek1)));

        List<EventKind> lek = eventKindService.findAllByIdLike(1L);

        assertEquals(lek.size(),1);
    }

    @Test
    void saveAll() {
        when(eventKindRepository.saveAll(anyIterable())).thenReturn(it);

        Iterable<EventKind> itr = eventKindService.saveAll(it);

        assertEquals(itr,it);
    }

    @Test
    void save() {
        when(eventKindRepository.save(any())).thenReturn(ek1);
        EventKind ek =  eventKindService.save(ek1);

        assertEquals(ek.getId(),ek1.getId());
    }
}