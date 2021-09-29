package net.trpcp.silosy.controllers;

import net.trpcp.silosy.exceptions.BadHttpRequest;
import net.trpcp.silosy.exceptions.NotFoundException1;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadHttpRequest.class)
    public ModelAndView handleNumberProblems(BadHttpRequest e){
        ModelAndView mav = new ModelAndView("badNumber");
        mav.addObject("exception", e);
        return mav;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException1.class) //whatever is put here will bind invocation of that method to exeption thrown, wheter its custom or core exeptions
    public ModelAndView handleNotFound(NotFoundException1 e){
        ModelAndView mav = new ModelAndView("notfound");
        mav.addObject("exception", e);
        return mav;
    }
}
