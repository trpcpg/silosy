package net.trpcp.silosy.repositories;

import net.trpcp.silosy.model.EventKind;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface EventKindRepository extends CrudRepository<EventKind, Long> {
    Set<EventKind> findAllByIdLike(Long id);
}
