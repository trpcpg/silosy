package net.trpcp.silosy.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class TestControler {

    @GetMapping("/")
    public String showTest(){
        return "test";
    }
}
