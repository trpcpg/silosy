package net.trpcp.silosy.controllers;

import lombok.extern.slf4j.Slf4j;
import net.trpcp.silosy.model.Person;
import net.trpcp.silosy.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Set;

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
        model.addAttribute("persons", personSet);
        Person newp = new Person();
        model.addAttribute("newp", newp);
        return "person";
    }

    @PostMapping("modperson/{ido}")
    public String modPerson(@Valid @ModelAttribute("newp") Person person, BindingResult bindingResult, @PathVariable("ido") String id, @ModelAttribute("persons") ArrayList<Person> persons, Model model){
        if(bindingResult.hasErrors()){
            Iterable<Person> personSet = personService.findAll();
            model.addAttribute("persons", personSet);
            System.out.println("-------blad-------");
            return "person";
        }
        person.setId(Long.valueOf(id));
        Person savedPerson = personService.save(person);
        return "redirect:/person";
    }

    @PostMapping("person")
    public String addPerson(Model model, @Valid @ModelAttribute("newp") Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Iterable<Person> personSet = personService.findAll();
            model.addAttribute("persons", personSet);
            System.out.println("-------blad-------");
            return "person";
        }
        Person savedPerson = personService.save(person);
        return "redirect:/person";
    }

    @GetMapping("modperson/{id}")
    public String modPerson(@PathVariable String id, Model model){
        Iterable<Person> personSet = personService.findAll();
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
