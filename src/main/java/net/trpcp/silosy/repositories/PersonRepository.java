package net.trpcp.silosy.repositories;

import net.trpcp.silosy.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {
    Person findByFirstName(String s);
    Person findByLastName(String s);
}
