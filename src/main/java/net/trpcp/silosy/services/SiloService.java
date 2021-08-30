package net.trpcp.silosy.services;

import net.trpcp.silosy.model.Silo;

import java.util.Set;

public interface SiloService {

    Iterable<Silo> findAll();

    Silo findById(Long id);

    Silo findByName(String s);

    Set<Silo> findByNameLike(String s);

    Set<Silo> findByCapacity(Integer i);

    Silo save(Silo silo);

    Iterable<Silo> saveAll(Set<Silo> silos);

    void delete(Silo silo);
}
