package net.trpcp.silosy.services;

import net.trpcp.silosy.model.Person;
import net.trpcp.silosy.model.Ware;

import java.util.Set;

public interface PersonService {

    Person findById(Long l);
    Person findByFirstName(String s);
    Person findByLastName(String s);
    Iterable<Person> findAll();
    Set<Person> findByFirstNameLike(String s);
    Set<Person> findByLastNameLike(String s);
    Person save(Person p);
    Iterable<Person> saveAll(Set<Person> set);
    void delete(Person p);
    void deleteById(Long l);
}
