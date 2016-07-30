package br.edu.ufcg.controllers;

import br.edu.ufcg.models.Solution;
import br.edu.ufcg.models.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/solution")
public class SolutionController {

    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Solution> createSolution(@RequestBody Solution solution){
        return new ResponseEntity<>(solution, HttpStatus.OK);
    }
}
