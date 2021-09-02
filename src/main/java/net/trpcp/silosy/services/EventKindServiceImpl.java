package net.trpcp.silosy.services;

import net.trpcp.silosy.model.EventKind;
import net.trpcp.silosy.repositories.EventKindRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventKindServiceImpl implements EventKingService{

    EventKindRepository eventKindRepository;

    public EventKindServiceImpl(EventKindRepository eventKindRepository) {
        this.eventKindRepository = eventKindRepository;
    }

    public EventKind findById(Long id){
        return eventKindRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<EventKind> findAll() {
        return eventKindRepository.findAll();
    }

    @Override
    public Iterable<EventKind> findForEmpty() {
        Iterable<Long> i = new HashSet(Arrays.asList(1,3,7));
        return eventKindRepository.findAllById(i);
    }

    @Override
    public Iterable<EventKind> findForFull() {
        Iterable<Long> i = new HashSet(Arrays.asList(2,4,5));
        return eventKindRepository.findAllById(i);
    }

    @Override
    public Iterable<EventKind> findForGased() {
        return new HashSet(Arrays.asList(eventKindRepository.findById(6L).orElse(null)));
    }

    @Override
    public Iterable<EventKind> findForBlocked() {
        return new HashSet(Arrays.asList(eventKindRepository.findById(9L).orElse(null)));
    }

    @Override
    public Iterable<EventKind> saveAll(Iterable<EventKind> eventKinds) {
        return eventKindRepository.saveAll(eventKinds);
    }

    @Override
    public EventKind save(EventKind e) {
        return eventKindRepository.save(e);
    }
}
