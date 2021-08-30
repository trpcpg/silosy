package net.trpcp.silosy.controllers;

import lombok.extern.slf4j.Slf4j;
import net.trpcp.silosy.model.Silo;
import net.trpcp.silosy.services.SiloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class SiloController {

    private final SiloService siloService;
    String ss;
    List<Silo> s;

    public SiloController(SiloService siloService) {
        this.siloService = siloService;
    }

    @GetMapping({"silo"})
    public ModelAndView showSilos() {
        ss = String.valueOf(LocalDateTime.now());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("silo");
        Iterable<Silo> s1 = siloService.findAll();
        s = new ArrayList<>();
        s1.forEach(s::add);
        mav.addObject("silos",s);
        Silo silo = new Silo();
        mav.addObject("news", silo);
        mav.addObject("ss",ss);
        return mav;
    }

    @GetMapping({"modsilo/{nr}"})
    public ModelAndView modSilos(@PathVariable("nr") int nr) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("silo");
        mav.addObject("silos",s);
        Silo silo = s.get(nr);
        mav.addObject("news", silo);
        mav.addObject("ss",ss);
        return mav;
    }

    @PostMapping("modsilo/{nr}")
    public ModelAndView updateSilo(@PathVariable("nr") int nr, @ModelAttribute("news") Silo silo){
        silo.setId(s.get(nr).getId());
        siloService.save(silo);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/silo");
        return mav;
    }

    @PostMapping("silo")
    public ModelAndView addSilo(@ModelAttribute("news") Silo silo){
        siloService.save(silo);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/silo");
        return mav;
    }

    @GetMapping("delsilo/{nr}")
    public ModelAndView delSilo(@PathVariable("nr") int nr){
        siloService.delete(s.get(nr));
        return new ModelAndView("redirect:/silo");
    }
}
