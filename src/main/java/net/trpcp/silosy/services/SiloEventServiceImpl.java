package net.trpcp.silosy.services;

import lombok.extern.slf4j.Slf4j;
import net.trpcp.silosy.model.Person;
import net.trpcp.silosy.model.Silo;
import net.trpcp.silosy.model.SiloEvent;
import net.trpcp.silosy.model.Ware;
import net.trpcp.silosy.repositories.PersonRepository;
import net.trpcp.silosy.repositories.SiloEventRepository;
import net.trpcp.silosy.repositories.SiloRepository;
import net.trpcp.silosy.repositories.WareRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
public class SiloEventServiceImpl implements SiloEventService{

    SiloEventRepository siloEventRepository;
    SiloRepository siloRepository;
    PersonRepository personRepository;
    WareRepository wareRepository;

    public SiloEventServiceImpl(SiloEventRepository siloEventRepository, SiloRepository siloRepository, PersonRepository personRepository, WareRepository wareRepository) {
        this.siloEventRepository = siloEventRepository;
        this.siloRepository = siloRepository;
        this.personRepository = personRepository;
        this.wareRepository = wareRepository;
    }

    @Override
    public SiloEvent findById(Long l) {
        return siloEventRepository.findById(l).orElse(null);
    }

    @Override
    public Set<SiloEvent> findByEventTime(LocalDateTime localDateTime) {
        return siloEventRepository.findByEventTime(localDateTime);
    }

    @Override
    public Set<SiloEvent> findBySilo(String s) {
        Silo silo = siloRepository.findByName(s);
        return siloEventRepository.findBySilo(silo);
    }

    @Override
    public Set<SiloEvent> findByWare(Ware w) {
        return siloEventRepository.findByWare(w);
    }

    @Override
    public Set<SiloEvent> findByPerson(Person p) {
        return siloEventRepository.findByPerson(p);
    }

    @Override
    public Set<SiloEvent> findByDocument(String s) {
        return siloEventRepository.findByDocument(s);
    }

    @Override
    public Iterable<SiloEvent> findAll() {
        return siloEventRepository.findAll();
    }

    @Override
    public SiloEvent save(SiloEvent se) {
        return siloEventRepository.save(se);
    }

    @Override
    public void delete(SiloEvent se) {
        siloEventRepository.delete(se);
    }

    @Override
    public void deleteById(Long l) {
        siloEventRepository.deleteById(l);
    }
}
