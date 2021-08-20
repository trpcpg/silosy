package net.trpcp.silosy.services;

import net.trpcp.silosy.model.Ware;

import java.util.Set;

public interface WareService {

    Ware findById(Long l);
    Ware findByName(String s);
    Set<Ware> findAll();
    Set<Ware> findByNameLike(String s);
    Ware save(Ware w);
    Set<Ware> saveAll(Set<Ware> set);
    void delete(Ware w);
    void deleteById(Long l);

}
