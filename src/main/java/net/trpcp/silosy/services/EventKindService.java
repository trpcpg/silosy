package net.trpcp.silosy.services;

import net.trpcp.silosy.model.EventKind;

import java.util.List;

public interface EventKindService {
    EventKind findById(Long id);
    List<EventKind> findAll();
    List<EventKind> findForEmpty();
    List<EventKind> findForFull();
    List<EventKind> findForUsed();
    List<EventKind> findForGased();
    List<EventKind> findForBlocked();
    List<EventKind> findAllByIdLike(Long id);
    Iterable<EventKind> saveAll(Iterable<EventKind> eventKinds);
    EventKind save(EventKind e);
}
