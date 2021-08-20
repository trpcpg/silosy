package net.trpcp.silosy.services;

import net.trpcp.silosy.model.Silo;

import java.util.Set;

public interface SiloService {

    Silo findByName(String s);

    Set<Silo> findByNameLike(String s);

    Set<Silo> findByCapacity(Integer i);

    Iterable<Silo> saveAll(Set<Silo> silos);
}
