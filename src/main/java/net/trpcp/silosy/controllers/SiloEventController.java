package net.trpcp.silosy.controllers;

import net.trpcp.silosy.model.Silo;
import net.trpcp.silosy.model.SiloEvent;
import net.trpcp.silosy.services.PersonService;
import net.trpcp.silosy.services.SiloEventService;
import net.trpcp.silosy.services.SiloService;
import net.trpcp.silosy.services.WareService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Set;

@Controller
public class SiloEventController {

    private final PersonService personService;
    private final WareService wareService;
    private final SiloEventService siloEventService;
    private final SiloService siloService;

    public SiloEventController(PersonService personService, WareService wareService, SiloEventService siloEventService, SiloService siloService) {
        this.personService = personService;
        this.wareService = wareService;
        this.siloEventService = siloEventService;
        this.siloService = siloService;
    }

    @GetMapping("siloevent/{nr}")
    public ModelAndView getEvents(@PathVariable("nr") Long id){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/siloevent");
        //Set<SiloEvent> list = siloEventService.findBySilo(siloService.findById(id));
        //System.out.println(id);
        //System.out.println(list.size());
        mav.addObject("events", siloEventService.findBySilo(siloService.findById(id)));
        mav.addObject("newse", new SiloEvent());
        mav.addObject("wares", wareService.findAll());
        mav.addObject("persons", personService.findAll());
        return mav;
    }

    @PostMapping("siloevent/{nr}")
    public ModelAndView saveEvent(@ModelAttribute("newse") SiloEvent siloEvent, @PathVariable("nr") Long id /*, @RequestParam("eventTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateAndTime*/){
        ModelAndView mav = new ModelAndView("siloevent");
        Silo silo = siloService.findById(id);
        //siloEvent.setEventTime(dateAndTime);
        siloEvent.setSilo(silo);
        siloEventService.save(siloEvent);
        //Set<SiloEvent> list = siloEventService.findBySilo(siloService.findById(id));
        mav.addObject("events", siloEventService.findBySilo(siloService.findById(id)));
        mav.addObject("newse", new SiloEvent());
        mav.addObject("wares", wareService.findAll());
        mav.addObject("persons", personService.findAll());
        return mav;
    }
}
