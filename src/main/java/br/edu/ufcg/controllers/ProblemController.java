package br.edu.ufcg.controllers;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/problem", produces="application/json")
public class ProblemController {

    @Autowired
    ProblemService service;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity< List<Problem>> getProblems(
            @RequestParam(value = "user", required = false) Long user){

        List<Problem> problems;

        if(user == null){
            problems = service.findAll();
        }else{
            problems = service.findByOwner(user);
        }

        return new ResponseEntity<>(problems, HttpStatus.OK);
    }

    @RequestMapping(value="/{problemCode}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Problem> getProblem(@PathVariable Long problemCode){
        Problem  problem = service.getProblem(problemCode);
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem){
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }

    @RequestMapping(value="/{problemCode}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Problem> updateProblem(@PathVariable Long problemCode, @RequestBody Problem problem){
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }

    @RequestMapping(value="/{problemCode}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Problem> deleteProblem(@PathVariable Long problemCode, @RequestBody Problem problem){
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }
}
