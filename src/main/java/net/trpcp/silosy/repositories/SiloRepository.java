package net.trpcp.silosy.repositories;

import net.trpcp.silosy.model.Silo;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface SiloRepository extends CrudRepository<Silo, Long> {

    Silo findByName(String s);

    Set<Silo> findByNameLike(String s);

    Set<Silo> findByCapacity(Integer i);

    Set<Silo> findByOrderByName();
}
