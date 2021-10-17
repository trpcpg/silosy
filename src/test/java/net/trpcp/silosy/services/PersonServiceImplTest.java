package net.trpcp.silosy.services;

import net.trpcp.silosy.model.Person;
import net.trpcp.silosy.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PersonServiceImplTest {

    @Mock
    PersonRepository personRepository;

    PersonService personService;

    Person p1;
    Person p2;
    Person p3;
    HashSet<Person> ps;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this );
        personService  = new PersonServiceImpl(personRepository);

        p1 = Person.builder().id(1L).firstName("fn1").lastName("ln1").build();
        p2 = Person.builder().id(2L).firstName("fn2").lastName("ln2").build();
        p3 = Person.builder().id(3L).firstName("fn3").lastName("ln3").build();
        ps = new HashSet<>(Arrays.asList(p1,p2,p3));
    }

    @Test
    void findById() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(p1));
        Person p = personService.findById(1L);
        assertEquals(p.getId(),1L);
    }

    @Test
    void findByFirstName() {
        String anyString = "fn1";
        when(personRepository.findByFirstName(anyString())).thenReturn(Person.builder().firstName(anyString).build());
        Person p =  personService.findByFirstName(anyString);
        assertEquals(p.getFirstName(),anyString);
    }

    @Test
    void findByLastName() {
        String anyString = "ln1";
        when(personRepository.findByLastName(anyString())).thenReturn(Person.builder().lastName(anyString).build());
        Person p =  personService.findByLastName(anyString);
        assertEquals(p.getLastName(),anyString);
    }

    @Test
    void findAll() {
        when(personRepository.findAll()).thenReturn(ps);
        Set<Person> ip = (Set<Person>) personService.findAll();
        assertEquals(ip.size(),3);
    }

    @Test
    void findByFirstNameLike() {
        when(personRepository.findByFirstNameLike(anyString())).thenReturn(new HashSet<>(Arrays.asList(p1,p2)));
        Set<Person> sp = personService.findByFirstNameLike("");
        assertEquals(sp.size(),2);
    }

    @Test
    void findByLastNameLike() {
        when(personRepository.findByLastNameLike(anyString())).thenReturn(new HashSet<>(Arrays.asList(p1,p2)));
        Set<Person> sp = personService.findByLastNameLike("");
        assertEquals(sp.size(),2);
    }

    @Test
    void save() {
        when(personRepository.save(any())).thenReturn(p1);
        Person p = personService.save(p1);
        assertEquals(p1.getId(),p.getId());
    }

    @Test
    void saveAll() {
        when(personRepository.saveAll(any())).thenReturn(ps);
        Iterable<Person> pi = personService.saveAll(ps);
        int i = 0;
        for(Object e:pi){
            i++;
        }
        assertEquals(ps.size(),i);
    }

    @Test
    void delete() {
        personService.delete(p1);
        verify(personRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        personService.deleteById(1L);
        verify(personRepository, times(1)).deleteById(anyLong());
    }
}