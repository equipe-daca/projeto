package br.edu.ufcg.controllers;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.Test;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/problem")
public class ProblemController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<Problem> getProblems(){
        return new ArrayList<Problem>();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Problem getProblem(@PathVariable Long id){
        return new Problem(id, "Nome", "Descrição", "Dica", new ArrayList<Test>());
    }
}
