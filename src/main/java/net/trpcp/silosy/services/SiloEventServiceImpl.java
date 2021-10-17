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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class SiloEventServiceImpl implements SiloEventService{

    SiloEventRepository siloEventRepository;
    SiloService siloService;
    PersonService personService;
    WareService wareService;

    public SiloEventServiceImpl(SiloEventRepository siloEventRepository, SiloService siloService, PersonService personService, WareService wareService) {
        this.siloEventRepository = siloEventRepository;
        this.siloService = siloService;
        this.personService = personService;
        this.wareService = wareService;
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
    public Set<SiloEvent> findBySilo(Silo s) {
        //Silo silo = siloRepository.findByName(s);
        return siloEventRepository.findBySilo(s);
    }

    @Override
    public Set<SiloEvent> findBySiloName(String s) {
        Silo it = siloService.findByName(s);
        return siloEventRepository.findBySilo(it);
    }

    @Override
    public Set<SiloEvent> findBySilo_id(Long id) {
        Optional<Silo> o = siloService.findById(id);
        if(o.isPresent()){
            return siloEventRepository.findBySilo(o.get());
        }
        else{
            return new HashSet<>();
        }
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
    public Set<SiloEvent> findBySiloOrderByEventTimeDesc(Silo s) {
        return siloEventRepository.findBySiloOrderByEventTimeDesc(s);
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
