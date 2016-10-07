package br.edu.ufcg.web.v2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("indexControlerV2")
@RequestMapping(value="/v2/")
public class IndexController {

    @RequestMapping(value="/", method=RequestMethod.GET, produces="text/plain")
    public String getIndex(){
        return "Servidor On";
    }
}
