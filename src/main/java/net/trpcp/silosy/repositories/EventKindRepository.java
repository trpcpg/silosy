package net.trpcp.silosy.repositories;

import net.trpcp.silosy.model.EventKind;
import org.springframework.data.repository.CrudRepository;

public interface EventKindRepository extends CrudRepository<EventKind, Long> {
}
