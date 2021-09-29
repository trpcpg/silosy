package net.trpcp.silosy.controllers;

import net.trpcp.silosy.exceptions.NotFoundException1;
import net.trpcp.silosy.model.EventKind;
import net.trpcp.silosy.model.Silo;
import net.trpcp.silosy.model.SiloEvent;
import net.trpcp.silosy.model.Ware;
import net.trpcp.silosy.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
        Optional<Silo> os1 = siloService.findById(id);
        if(os1.isEmpty()){
            throw new NotFoundException1("Nie odnaleziono silosa nr " + id);
        }
        Silo s = os1.get();
        mav.addObject("silo", s);
        Iterable<Silo> siloset = siloService.findAllOrderByName();
        siloset.forEach(silo -> {
            if(silo.getWare()==null) silo.setWare(Ware.builder().name("").build());
        });
        mav.addObject("silos", siloset);
        mav.addObject("events", siloEventService.findBySilo(s));
        mav.addObject("siloevent", SiloEvent.builder().eventTime(LocalDateTime.now()).build());
        if(s.getWare().getId()==null){
            mav.addObject("wares", wareService.findAll());
        }
        else{
            mav.addObject("wares", s.getWare());
        }
        mav.addObject("persons", personService.findAll());
        Iterable<SiloEvent> ses = siloEventService.findBySiloOrderByEventTimeDesc(s);
        List<SiloEvent> sesl= new ArrayList<>();
        ses.forEach(sesl::add);
        SiloEvent se;
        if(sesl.size()>0) se = sesl.get(0);
        else se = new SiloEvent();
        List<EventKind> eks;
        if (s.getStored() == 0 || siloEventService.findBySilo(s).isEmpty()) {
            System.out.println("----- empty -----");
            eks = eventKindService.findForEmpty();
        }
        else if (s.getCapacity() * 0.999 < s.getStored()) {
            System.out.println("----- full -----");
            eks = eventKindService.findForFull();
        }
        else if(s.getCapacity()*0.999>=s.getStored() && s.getStored()>=0){
            System.out.println("----- used -----");
            eks = eventKindService.findForUsed();
            if(s.getStored()<s.getCapacity()*0.003){
                System.out.println("----- and clear -----");
                eks.add(eventKindService.findById(7L));
            }
        }
        else if (se.getEventKind().getId() == 5L) {
            System.out.println("----- gas -----");
            eks = eventKindService.findForGased();
        }
        else{
            System.out.println("----- else -----");
            eks = eventKindService.findAll();
        }
        mav.addObject("eks", eks);
        return mav;
    }

    @PostMapping("siloevent/{nr}")
    public String saveEvent(@Valid @ModelAttribute("siloevent") SiloEvent siloEvent, BindingResult bindingResult, @ModelAttribute("targetsilo") Silo targetSilo, @PathVariable("nr") Long id, Model model) {

        if(bindingResult.hasErrors()){

            Optional<Silo> os1 = siloService.findById(id);
            if(os1.isEmpty()){
                throw new NotFoundException1("Nie odnaleziono silosa nr " + id);
            }
            Silo s = os1.get();
            model.addAttribute("silo", s);
            Iterable<Silo> siloset = siloService.findAllOrderByName();
            siloset.forEach(silo -> {
                if(silo.getWare()==null) silo.setWare(Ware.builder().name("").build());
            });
            model.addAttribute("silos", siloset);
            model.addAttribute("events", siloEventService.findBySilo(s));
            //model.addAttribute("siloevent", siloEvent);
            if(s.getWare().getId()==null){
                model.addAttribute("wares", wareService.findAll());
            }
            else{
                model.addAttribute("wares", s.getWare());
            }
            model.addAttribute("persons", personService.findAll());
            Iterable<SiloEvent> ses = siloEventService.findBySiloOrderByEventTimeDesc(s);
            List<SiloEvent> sesl= new ArrayList<>();
            ses.forEach(sesl::add);
            SiloEvent se;
            if(sesl.size()>0) se = sesl.get(0);
            else se = new SiloEvent();
            List<EventKind> eks;
            if (s.getStored() == 0 || siloEventService.findBySilo(s).isEmpty()) {
                System.out.println("----- empty -----");
                eks = eventKindService.findForEmpty();
            }
            else if (s.getCapacity() * 0.999 < s.getStored()) {
                System.out.println("----- full -----");
                eks = eventKindService.findForFull();
            }
            else if(s.getCapacity()*0.999>=s.getStored() && s.getStored()>=0){
                System.out.println("----- used -----");
                eks = eventKindService.findForUsed();
                if(s.getStored()<s.getCapacity()*0.003){
                    System.out.println("----- and clear -----");
                    eks.add(eventKindService.findById(7L));
                }
            }
            else if (se.getEventKind().getId() == 5L) {
                System.out.println("----- gas -----");
                eks = eventKindService.findForGased();
            }
            else{
                System.out.println("----- else -----");
                eks = eventKindService.findAll();
            }
            model.addAttribute("eks", eks);

            bindingResult.getAllErrors().forEach(objectError -> System.out.println("-----"+objectError.toString()+"-----"));
            return "siloevent";
        }

        //ModelAndView mav = new ModelAndView("siloevent");
        //System.out.println(targetSilo.getName());
        Optional<Silo> os1 = siloService.findById(id);
        if(os1.isEmpty()){
            throw new NotFoundException1("Nie odnaleziono silosa nr " + id);
        }
        Silo s = os1.get();
        siloEvent.setSilo(s);
        model.addAttribute("silo", s);
        if(siloEvent.getEventKind().getFactor()!=0){
            if(siloEvent.getEventKind().getId()==6L){
                s.setStatus(0);
                siloEvent.setQuantity(s.getStored());
            }
            //totaj też rozdział na operacje z siblingiem
            float amount = siloEvent.getQuantity()*siloEvent.getEventKind().getFactor();
            if((s.getWare()==null || s.getStored()==0) && s.getId().equals(id)) s.setWare(siloEvent.getWare());
            s.setStored(s.getStored()+amount);
            if(siloEvent.getEventKind().getId()==3L || siloEvent.getEventKind().getId()==4L || siloEvent.getEventKind().getId()==6L){
                targetSilo.setStored(targetSilo.getStored()-amount);
                SiloEvent targetEvent = SiloEvent.builder()
                        .quantity(siloEvent.getQuantity())
                        .silo(targetSilo)
                        .eventTime(siloEvent.getEventTime())
                        .document(siloEvent.getDocument())
                        .ware(siloEvent.getWare())
                        .person(siloEvent.getPerson())
                        .build();
                if(siloEvent.getEventKind().getId()==3L){
                    targetEvent.setEventKind(eventKindService.findById(4L));
                    targetEvent.setDescription("przesunięcie z " + s.getName());
                }
                else if(siloEvent.getEventKind().getId()==4L){
                    targetEvent.setEventKind(eventKindService.findById(3L));
                    targetEvent.setDescription("przesunięcie na " + s.getName());
                }
                else if(siloEvent.getEventKind().getId()==6L){
                    targetEvent.setEventKind(eventKindService.findById(4L));
                    targetEvent.setDescription("wietrzenie z " + s.getName());
                }
                siloEventService.save(targetEvent);
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
                s.setStored(0f);
            }
        }

        siloEventService.save(siloEvent);
        siloService.save(s);

        Iterable<Silo> siloset = siloService.findAllOrderByName();
        siloset.forEach(silo -> {
            if(silo.getWare()==null) silo.setWare(Ware.builder().name("").build());
        });
        model.addAttribute("silos", siloset);
        model.addAttribute("events", siloEventService.findBySilo(s));
        model.addAttribute("siloevent", SiloEvent.builder().eventTime(LocalDateTime.now()).build());
        if(s.getWare().getId()==null) model.addAttribute("wares", wareService.findAll());
        else model.addAttribute("wares", s.getWare());
        model.addAttribute("persons", personService.findAll());

        Iterable<SiloEvent> ses = siloEventService.findBySiloOrderByEventTimeDesc(s);
        List<SiloEvent> sesl= new ArrayList<>();
        ses.forEach(sesl::add);
        SiloEvent se;
        if(sesl.size()>0) se = sesl.get(0);
        else se = new SiloEvent();

        List<EventKind> eks;

        if (s.getStored() == 0 || siloEventService.findBySilo(s).isEmpty()) {
            System.out.println("----- empty -----");
            eks = eventKindService.findForEmpty();
        }
        else if (s.getCapacity() * 0.999 < s.getStored()) {
            System.out.println("----- full -----");
            eks = eventKindService.findForFull();
        }
        else if(s.getCapacity()*0.999>=s.getStored() && s.getStored()>=0){
            System.out.println("----- used -----");
            eks = eventKindService.findForUsed();
            if(s.getStored()<s.getCapacity()*0.003){
                System.out.println("----- and clear -----");
                eks.add(eventKindService.findById(7L));
            }
        }
        else if (se.getEventKind().getId() == 5L) {
            System.out.println("----- gas -----");
            eks = eventKindService.findForGased();
        }
        else{
            System.out.println("----- else -----");
            eks = eventKindService.findAll();
        }
        model.addAttribute("eks", eks);

        return "siloevent";
    }
}
