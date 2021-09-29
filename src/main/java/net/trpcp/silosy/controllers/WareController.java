package net.trpcp.silosy.controllers;

import lombok.extern.slf4j.Slf4j;
import net.trpcp.silosy.model.Ware;
import net.trpcp.silosy.services.WareService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class WareController {

    private final WareService wareService;

    public WareController(WareService wareService) {
        this.wareService = wareService;
    }

    @GetMapping("ware")
    public ModelAndView getWares(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ware");
        mav.addObject("wares", wareService.findAll());
        mav.addObject("neww", new Ware());
        return mav;
    }

    @GetMapping("modware/{nr}")
    public ModelAndView modWare(@PathVariable("nr") Long id){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ware");
        mav.addObject("wares", wareService.findAll());
        mav.addObject("neww", wareService.findById(id));
        return mav;
    }

    @PostMapping("ware")
    public ModelAndView addWare(@Valid @ModelAttribute("neww") Ware ware, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            System.out.println("--------sdfghsdfgh---------");
            mav.addObject("wares", wareService.findAll());
            mav.addObject("neww", ware);
            mav.setViewName("ware");
            return  mav;
        }
        Ware savedWare = wareService.save(ware);
        mav.setViewName("redirect:/ware");
        mav.addObject("wares", wareService.findAll());
        return mav;
    }

    @PostMapping("modware/{nr}")
    public ModelAndView modWare(@Valid @ModelAttribute("neww") Ware ware,BindingResult bindingResult, @PathVariable("nr") Long id){
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            mav.addObject("wares", wareService.findAll());
            mav.addObject("neww", ware);
            mav.setViewName("ware");
            return  mav;
        }
        ware.setId(id);
        Ware savedWare = wareService.save(ware);
        mav.setViewName("redirect:/ware");
        mav.addObject("wares", wareService.findAll());
        return mav;
    }

    @GetMapping("delware/{nr}")
    public ModelAndView delWare(@PathVariable("nr") Long id){
        wareService.deleteById(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/ware");
        return mav;
    }
}
