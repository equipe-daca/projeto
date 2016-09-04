package br.edu.ufcg.web;

import br.edu.ufcg.domain.Solution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/solution", produces="application/json")
public class SolutionController {

    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Solution> createSolution(@RequestBody Solution solution){
        return new ResponseEntity<>(solution, HttpStatus.OK);
    }
}
