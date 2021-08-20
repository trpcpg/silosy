package net.trpcp.silosy.bootstrap;

import lombok.extern.slf4j.Slf4j;
import net.trpcp.silosy.model.Person;
import net.trpcp.silosy.model.Silo;
import net.trpcp.silosy.model.SiloEvent;
import net.trpcp.silosy.model.Ware;
import net.trpcp.silosy.repositories.PersonRepository;
import net.trpcp.silosy.repositories.WareService;
import net.trpcp.silosy.services.SiloEventService;
import net.trpcp.silosy.services.SiloService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final SiloService siloService;
    private final WareService wareService;
    private final PersonRepository personRepository;
    private final SiloEventService siloEventService;
    boolean init = false;

    public DataLoader(SiloService siloService, WareService wareService, PersonRepository personRepository, SiloEventService siloEventService) {
        this.siloService = siloService;
        this.wareService = wareService;
        this.personRepository = personRepository;
        this.siloEventService = siloEventService;
    }

    @Override
    public void run(String... args){

        if(!init){
            synchronized (this) {
                loadData();
                init = true;
            }
        }
    }

    public void loadData() {

        Set<Silo> set = new HashSet<>();

        set.add(Silo.builder().name("Silo1").capacity(13200).build());
        set.add(Silo.builder().name("Silo2").capacity(13400).build());
        set.add(Silo.builder().name("Silo3").capacity(12900).build());
        set.add(Silo.builder().name("Silo4").capacity(13100).build());

        Iterable<Silo> savedSilos = siloService.saveAll(set);
        List<Silo> siloList = new ArrayList<>();
        savedSilos.forEach(siloList::add);

        Set<Ware> wares = new HashSet<>();

        wares.add(Ware.builder().name("przenica").build());
        wares.add(Ware.builder().name("kukurydza").build());
        wares.add(Ware.builder().name("żyto").build());
        wares.add(Ware.builder().name("jęczmień").build());
        wares.add(Ware.builder().name("popiół").build());
        wares.add(Ware.builder().name("rzepak").build());

        Iterable<Ware> savedWares = wareService.saveAll(wares);
        List<Ware> wareList = new ArrayList<>();
        savedWares.forEach(wareList::add);

        Set<Person> persons = new HashSet<>();

        persons.add(Person.builder().firstName("Barbara").lastName("Bramowska").build());
        persons.add(Person.builder().firstName("Bożena").lastName("Kuropatwa").build());
        persons.add(Person.builder().firstName("Sławek").lastName("Wszechmocny").build());

        Iterable<Person> savedPersons = personRepository.saveAll(persons);
        List<Person> personList = new ArrayList<>();
        savedPersons.forEach(personList::add);

        SiloEvent se = SiloEvent.builder()
                .eventTime(LocalDateTime.of(2021,8,17,15,4))
                .silo(siloList.get(0))
                .person(personList.get(0))
                .ware(wareList.get(0))
                .document("PZ/23/432/2021")
                .description("wilgoć")
                .quantity(2000)
                .build();

        siloEventService.save(se);

        SiloEvent se1 = SiloEvent.builder()
                .eventTime(LocalDateTime.of(2021,8,17,15,4))
                .silo(siloList.get(1))
                .person(personList.get(1))
                .ware(wareList.get(1))
                .document("PZ/33/455/2021")
                .description("robaki")
                .quantity(3000)
                .build();

        siloEventService.save(se1);

        Set<Silo> s = siloService.findByNameLike("Silo%");
        System.out.println(s.size());

        System.out.println(personRepository.findByFirstName("Barbara").getLastName());
        System.out.println(wareService.findAll().getClass());
        System.out.println(siloEventService.findBySilo("Silo1").iterator().next().getDocument());
        siloEventService.findAll().forEach(System.out::println);
    }

}
