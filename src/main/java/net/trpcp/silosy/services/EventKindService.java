package net.trpcp.silosy.services;

import net.trpcp.silosy.model.EventKind;

import java.util.List;
import java.util.Set;

public interface EventKindService {
    EventKind findById(Long id);
    List<EventKind> findAll();
    List<EventKind> findForEmpty();
    List<EventKind> findForFull();
    List<EventKind> findForGased();
    List<EventKind> findForBlocked();
    Iterable<EventKind> saveAll(Iterable<EventKind> eventKinds);
    EventKind save(EventKind e);
}
