package net.trpcp.silosy.repositories;

import net.trpcp.silosy.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PersonRepository extends CrudRepository<Person,Long> {
    Person findByFirstName(String s);
    Person findByLastName(String s);
    Set<Person> findByFirstNameLike(String s);
    Set<Person> findByLastNameLike(String s);
}
