package net.trpcp.silosy.repositories;

import net.trpcp.silosy.model.Ware;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface WareService extends CrudRepository<Ware,Long> {
    Ware findByName(String s);
    Set<Ware> findByNameLike(String s);
}
