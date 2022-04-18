package net.trpcp.silosy.services;

import net.trpcp.silosy.model.Silo;

import java.util.Optional;
import java.util.Set;

public interface SiloService {

    Iterable<Silo> findAll();

    Iterable<Silo> findAllOrderByName();

    Optional<Silo> findById(Long id);

    Silo findByName(String s);
    Set<Silo> findByNameLike(String s);

    Set<Silo> findByCapacity(Integer i);

    Silo save(Silo silo);

    Iterable<Silo> saveAll(Set<Silo> silos);

    void delete(Silo silo);

    Iterable<Silo> findAllByOrderById();
}
