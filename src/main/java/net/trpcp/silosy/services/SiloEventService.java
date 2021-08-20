package net.trpcp.silosy.services;

import net.trpcp.silosy.model.Person;
import net.trpcp.silosy.model.Silo;
import net.trpcp.silosy.model.SiloEvent;
import net.trpcp.silosy.model.Ware;

import java.time.LocalDateTime;
import java.util.Set;

public interface SiloEventService {

    SiloEvent findById(Long l);
    Set<SiloEvent> findByEventTime(LocalDateTime localDateTime);
    Set<SiloEvent> findBySilo(String s);
    Set<SiloEvent> findByWare(Ware w);
    Set<SiloEvent> findByPerson(Person p);
    Set<SiloEvent> findByDocument(String s);
    Set<SiloEvent> findAll();
    SiloEvent save(SiloEvent se);
    void delete(SiloEvent se);
    void deleteById(Long l);
}
