package br.edu.ufcg.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class IndexController {

    @RequestMapping(method=RequestMethod.GET, produces="text/plain")
    public String getIndex(){
        return "Servidor On";
    }
}
