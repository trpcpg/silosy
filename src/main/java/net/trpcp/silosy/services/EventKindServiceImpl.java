package net.trpcp.silosy.services;

import net.trpcp.silosy.model.EventKind;
import net.trpcp.silosy.repositories.EventKindRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventKindServiceImpl implements EventKindService {

    EventKindRepository eventKindRepository;

    public EventKindServiceImpl(EventKindRepository eventKindRepository) {
        this.eventKindRepository = eventKindRepository;
    }

    public EventKind findById(Long id) {
        return eventKindRepository.findById(id).orElse(null);
    }

    @Override
    public List<EventKind> findAll() {
        Iterable<EventKind> iek = eventKindRepository.findAll();
        List<EventKind> lek = new ArrayList<>();
        iek.forEach(lek::add);
        return lek;
    }

    @Override
    public List<EventKind> findForEmpty() {
        List<EventKind> rl = new ArrayList<>();
        long[] l = {1L, 3L, 7L};
        for (long il : l) {
            rl.add(eventKindRepository.findById(il).orElse(null));
        }
        return rl;
    }

    @Override
    public List<EventKind> findForFull() {
        List<EventKind> rl = new ArrayList<>();
        long[] l = {2L, 4L, 5L};
        for (long il : l) {
            rl.add(eventKindRepository.findById(il).orElse(null));
        }
        return rl;
    }

    @Override
    public List<EventKind> findForGased() {
        List<EventKind> rl = new ArrayList<>();
        rl.add(eventKindRepository.findById(6L).orElse(null));
        return rl;
    }

    @Override
    public List<EventKind> findForBlocked() {
        List<EventKind> rl = new ArrayList<>();
        rl.add(eventKindRepository.findById(9L).orElse(null));
        return rl;
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
