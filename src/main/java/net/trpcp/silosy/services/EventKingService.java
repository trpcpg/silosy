package net.trpcp.silosy.services;

import net.trpcp.silosy.model.EventKind;

import java.util.Set;

public interface EventKingService {
    EventKind findById(Long id);
    Iterable<EventKind> findAll();
    Iterable<EventKind> findForEmpty();
    Iterable<EventKind> findForFull();
    Iterable<EventKind> findForGased();
    Iterable<EventKind> findForBlocked();
    Iterable<EventKind> saveAll(Iterable<EventKind> eventKinds);
    EventKind save(EventKind e);
}
