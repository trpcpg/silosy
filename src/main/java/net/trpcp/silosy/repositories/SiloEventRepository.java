package net.trpcp.silosy.repositories;

import net.trpcp.silosy.model.Person;
import net.trpcp.silosy.model.Silo;
import net.trpcp.silosy.model.SiloEvent;
import net.trpcp.silosy.model.Ware;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Set;

public interface SiloEventRepository extends CrudRepository<SiloEvent, Long> {
    Set<SiloEvent> findByEventTime(LocalDateTime localDateTime);
    Set<SiloEvent> findBySilo(Silo s);
    Set<SiloEvent> findByWare(Ware w);
    Set<SiloEvent> findByPerson(Person p);
    Set<SiloEvent> findByDocument(String s);
    Set<SiloEvent> findBySiloOrderByEventTimeDesc(Silo s);
}
