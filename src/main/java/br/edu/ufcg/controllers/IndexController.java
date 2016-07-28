package br.edu.ufcg.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class IndexController {

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getIndex(){
        return "Servidor On";
    }
}
