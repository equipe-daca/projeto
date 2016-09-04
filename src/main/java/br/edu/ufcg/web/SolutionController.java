package br.edu.ufcg.web;

import br.edu.ufcg.domain.Solution;
import br.edu.ufcg.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/solution", produces="application/json")
public class SolutionController {

    @Autowired
    SolutionService solutionService;

    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Solution> createSolution(@RequestBody Solution solution){

        Solution s = solutionService.save(solution);

        if(s != null){
            return new ResponseEntity<>(solution, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
