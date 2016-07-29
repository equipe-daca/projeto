package br.edu.ufcg.controllers;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value="/problem")
public class ProblemController {

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity< List<Problem>> getProblems(){
        List<Problem> problems = Collections.emptyList();
        return new ResponseEntity<>(problems, HttpStatus.OK);
    }

    @RequestMapping(value="/{code}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Problem> getProblem(@PathVariable Long code){
        Problem  problem = new Problem(code, "Nome", "Descrição", "Dica", new ArrayList<Test>());
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem){
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }

    @RequestMapping(value="/{code}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Problem> updateProblem(@PathVariable Long code, @RequestBody Problem problem){
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }

    @RequestMapping(value="/{code}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Problem> deleteProblem(@PathVariable Long code, @RequestBody Problem problem){
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }
}
