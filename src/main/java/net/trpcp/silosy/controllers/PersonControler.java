package net.trpcp.silosy.controllers;

import lombok.extern.slf4j.Slf4j;
import net.trpcp.silosy.model.Person;
import net.trpcp.silosy.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class PersonControler {

    private final PersonService personService;

    public PersonControler(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("person")
    public String showPerson(Model model){
        Iterable<Person> personSet = personService.findAll();
//        List<Person> pList = new ArrayList<>();
//        personSet.forEach(pList::add);
        model.addAttribute("persons", personSet);
        Person newp = new Person();
        model.addAttribute("newp", newp);
        return "person";
    }

    @PostMapping("modperson/{ido}")
    public String modPerson(Model model, @ModelAttribute("newp") Person person, @PathVariable("ido") String id){
        //person.setId(Long.valueOf(id));
        Person savedPerson = personService.save(person);
        return "redirect:/person";
    }

    @PostMapping("addperson/")
    public String addPerson(Model model, @ModelAttribute("newp") Person person){
        Person savedPerson = personService.save(person);
        return "redirect:/person/";
    }

    @GetMapping("modperson/{id}")
    public String modPerson(@PathVariable String id, Model model){
        Iterable<Person> personSet = personService.findAll();
//        List<Person> pList = new ArrayList<>();
//        personSet.forEach(pList::add);
        model.addAttribute("persons", personSet);
        Person newp = personService.findById(Long.valueOf(id));
        model.addAttribute("newp", newp);
        return "person";
    }

    @GetMapping("delperson/{ido}")
    public String delPerson(Model model, @PathVariable("ido") String id){
        personService.deleteById(Long.valueOf(id));
        return "redirect:/person";
    }
}
