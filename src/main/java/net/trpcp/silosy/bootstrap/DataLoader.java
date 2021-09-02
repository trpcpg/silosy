package net.trpcp.silosy.bootstrap;

import lombok.extern.slf4j.Slf4j;
import net.trpcp.silosy.model.*;
import net.trpcp.silosy.repositories.PersonRepository;
import net.trpcp.silosy.repositories.WareRepository;
import net.trpcp.silosy.services.*;
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
    private final PersonService personService;
    private final SiloEventService siloEventService;
    private final EventKingService eventKingService;
    boolean init = false;

    public DataLoader(SiloService siloService, WareService wareService, PersonService personService, SiloEventService siloEventService, EventKingService eventKingService) {
        this.siloService = siloService;
        this.wareService = wareService;
        this.personService = personService;
        this.siloEventService = siloEventService;
        this.eventKingService = eventKingService;
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

        Set<Silo> set = new HashSet<>();

        set.add(Silo.builder().name("Silo1").capacity(13200).ware(wareList.get(0)).amount(123).build());
        set.add(Silo.builder().name("Silo2").capacity(13400).ware(wareList.get(1)).amount(124).build());
        set.add(Silo.builder().name("Silo3").capacity(12900).ware(wareList.get(2)).amount(125).build());
        set.add(Silo.builder().name("Silo4").capacity(13100).ware(wareList.get(3)).amount(126).build());

        Iterable<Silo> savedSilos = siloService.saveAll(set);
        List<Silo> siloList = new ArrayList<>();
        savedSilos.forEach(siloList::add);

        Set<Person> persons = new HashSet<>();

        persons.add(Person.builder().firstName("Barbara").lastName("Bramowska").build());
        persons.add(Person.builder().firstName("Bożena").lastName("Kuropatwa").build());
        persons.add(Person.builder().firstName("Sławek").lastName("Wszechmocny").build());

        Iterable<Person> savedPersons = personService.saveAll(persons);
        List<Person> personList = new ArrayList<>();
        savedPersons.forEach(personList::add);

        List<EventKind> eventKindList = new ArrayList<>();
        eventKingService.save(EventKind.builder().id(1L).name("przyjęcie").factor(1).sibling(null).build());
        eventKingService.save(EventKind.builder().id(2L).name("wydanie").factor(-1).sibling(null).build());
        eventKingService.save(EventKind.builder().id(3L).name("przesunięcie na").factor(1).sibling(null).build());
        eventKingService.save(EventKind.builder().id(4L).name("przesunięcie z").factor(-1).sibling(eventKingService.findById(3L)).build());
        //eventKindList.get(2).setSibling(eventKindList.get(3));
        EventKind ev = eventKingService.findById(3L);
        ev.setSibling(eventKingService.findById(4L));
        eventKingService.save(ev);
        eventKingService.save(EventKind.builder().id(5L).name("gazowanie").factor(0).sibling(null).build());
        eventKingService.save(EventKind.builder().id(6L).name("wietrzenie").factor(-1).sibling(eventKingService.findById(1L)).build());
        eventKingService.save(EventKind.builder().id(7L).name("czyszczenie").factor(0).sibling(null).build());
        eventKingService.save(EventKind.builder().id(8L).name("zablokowany").factor(0).sibling(null).build());
        eventKingService.save(EventKind.builder().id(9L).name("odblokowany").factor(0).sibling(null).build());

        //eventKingService.saveAll(new HashSet(eventKindList));

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

        System.out.println(personService.findByFirstName("Barbara").getLastName());
        System.out.println(wareService.findAll().getClass());
        System.out.println(siloEventService.findBySilo(set.iterator().next()).iterator().next().getDocument());
        siloEventService.findAll().forEach(System.out::println);
    }

}
