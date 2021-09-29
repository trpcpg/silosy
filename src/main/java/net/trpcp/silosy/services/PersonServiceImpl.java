package net.trpcp.silosy.services;

import net.trpcp.silosy.exceptions.NotFoundException1;
import net.trpcp.silosy.model.Person;
import net.trpcp.silosy.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PersonServiceImpl implements net.trpcp.silosy.services.PersonService {

    PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findById(Long l) {
        Optional<Person> returnedPerson = personRepository.findById(l);
        if(returnedPerson.isEmpty()) throw new NotFoundException1("Osoba o podanym ID nie istnieje");
        return returnedPerson.get();
    }

    @Override
    public Person findByFirstName(String s) {
        return personRepository.findByFirstName(s);
    }

    @Override
    public Person findByLastName(String s) {
        return personRepository.findByLastName(s);
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Set<Person> findByFirstNameLike(String s) {
        return personRepository.findByFirstNameLike("%" + s + "%");
    }

    @Override
    public Set<Person> findByLastNameLike(String s) {
        return personRepository.findByLastNameLike("%" + s + "%");
    }

    @Override
    public Person save(Person p) {
        return personRepository.save(p);
    }

    @Override
    public Iterable<Person> saveAll(Set<Person> set) {
        return personRepository.saveAll(set);
    }

    @Override
    public void delete(Person p) {
        personRepository.delete(p);
    }

    @Override
    public void deleteById(Long l) {
        personRepository.deleteById(l);
    }
}
