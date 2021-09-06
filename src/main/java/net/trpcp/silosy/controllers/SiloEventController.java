package net.trpcp.silosy.controllers;

import net.trpcp.silosy.model.EventKind;
import net.trpcp.silosy.model.Silo;
import net.trpcp.silosy.model.SiloEvent;
import net.trpcp.silosy.model.Ware;
import net.trpcp.silosy.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SiloEventController {

    private final PersonService personService;
    private final WareService wareService;
    private final SiloEventService siloEventService;
    private final SiloService siloService;
    private final EventKindService eventKindService;

    public SiloEventController(PersonService personService, WareService wareService, SiloEventService siloEventService, SiloService siloService, EventKindService eventKindService) {
        this.personService = personService;
        this.wareService = wareService;
        this.siloEventService = siloEventService;
        this.siloService = siloService;
        this.eventKindService = eventKindService;
    }

    @GetMapping({"","index","/","index.html"})
    public ModelAndView getFirst(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        Iterable<Silo> siloset = siloService.findAllOrderByName();
        siloset.forEach(silo -> {
            if(silo.getWare()==null) silo.setWare(Ware.builder().name("").build());
        });
        mav.addObject("silos", siloset);
        return mav;
    }

    @GetMapping("siloevent/{nr}")
    public ModelAndView getEvents(@PathVariable("nr") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/siloevent");
        Silo s = siloService.findById(id);
        mav.addObject("silo", s);
        Iterable<Silo> siloset = siloService.findAllOrderByName();
        siloset.forEach(silo -> {
            if(silo.getWare()==null) silo.setWare(Ware.builder().name("").build());
        });
        mav.addObject("silos", siloset);
        mav.addObject("events", siloEventService.findBySilo(s));
        mav.addObject("newse", SiloEvent.builder().eventTime(LocalDateTime.now()).build());
        mav.addObject("wares", wareService.findAll());
        mav.addObject("persons", personService.findAll());
        Iterable<SiloEvent> ses = siloEventService.findBySiloOrderByEventTimeDesc(siloService.findById(id));
        List<SiloEvent> sesl= new ArrayList<>();
        ses.forEach(sesl::add);
        SiloEvent se;
        if(sesl.size()>0) se = sesl.get(0);
        else se = new SiloEvent();
        List<EventKind> eks;
        if (s.getStored() == 0 || siloEventService.findBySilo(s).isEmpty()) {
            eks = eventKindService.findForEmpty();
        }
        else if (s.getCapacity() < s.getStored() + s.getCapacity() * 0.05) {
            eks = eventKindService.findForFull();
        }
        else if (se.getEventKind().getId() == 5L) {
            eks = eventKindService.findForGased();
        }
        else{
            eks = eventKindService.findAll();
        }
        mav.addObject("eks", eks);
        return mav;
    }

    @PostMapping("siloevent/{nr}")
    public ModelAndView saveEvent(@ModelAttribute("newse") SiloEvent siloEvent, @ModelAttribute("targetsilo") Silo targetSilo, @PathVariable("nr") Long id) {
        ModelAndView mav = new ModelAndView("siloevent");
        //System.out.println(targetSilo.getName());
        Silo s = siloService.findById(id);
        siloEvent.setSilo(s);
        mav.addObject("silo", s);
        if(siloEvent.getEventKind().getFactor()!=0){
            if(siloEvent.getEventKind().getId()==6L){
                s.setStatus(0);
                siloEvent.setQuantity(s.getStored());
            }
            //totaj też rozdział na operacje z siblingiem
            int amount = siloEvent.getQuantity()*siloEvent.getEventKind().getFactor();
            if((s.getWare()==null || s.getStored()==0) && s.getId().equals(id)) s.setWare(siloEvent.getWare());
            s.setStored(s.getStored()+amount);
            if(siloEvent.getEventKind().getId()==3L || siloEvent.getEventKind().getId()==4L || siloEvent.getEventKind().getId()==6L){
                targetSilo.setStored(targetSilo.getStored()-amount);
            }
        }
        else{
            if(siloEvent.getEventKind().getId()==8L){
                s.setStatus(2);
            }
            else if(siloEvent.getEventKind().getId()==9L){
                s.setStatus(0);
            }
            else if(siloEvent.getEventKind().getId()==5L){
                s.setStatus(1);
            }
            else if(siloEvent.getEventKind().getId()==7L && s.getCapacity()*0.02>s.getStored()){
                s.setStatus(0);
                s.setWare(null);
                s.setStored(0);
            }
        }

        siloEventService.save(siloEvent);
        siloService.save(s);

        Iterable<Silo> siloset = siloService.findAllOrderByName();
        siloset.forEach(silo -> {
            if(silo.getWare()==null) silo.setWare(Ware.builder().name("").build());
        });
        mav.addObject("silos", siloset);
        mav.addObject("events", siloEventService.findBySilo(siloService.findById(id)));
        mav.addObject("newse", SiloEvent.builder().eventTime(LocalDateTime.now()).build());
        mav.addObject("wares", wareService.findAll());
        mav.addObject("persons", personService.findAll());

        Iterable<SiloEvent> ses = siloEventService.findBySiloOrderByEventTimeDesc(siloService.findById(id));
        List<SiloEvent> sesl= new ArrayList<>();
        ses.forEach(sesl::add);
        SiloEvent se;
        if(sesl.size()>0) se = sesl.get(0);
        else se = new SiloEvent();

        Iterable<EventKind> eks;

        if (s.getStored() == 0) {
            eks = eventKindService.findForEmpty();
        }
        else if (s.getCapacity() < s.getStored() + s.getCapacity() * 0.05) {
            eks = eventKindService.findForFull();
        }
        else if (se.getEventKind().getId() == 5L) {
            eks = eventKindService.findForGased();
        }
        else{
            eks = eventKindService.findAll();
        }
        mav.addObject("eks", eks);
        return mav;
    }
}
