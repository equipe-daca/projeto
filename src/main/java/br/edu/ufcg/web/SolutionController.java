package br.edu.ufcg.web;

import br.edu.ufcg.domain.Solution;
import br.edu.ufcg.domain.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/solution", produces="application/json")
public class SolutionController {

    @Autowired
    SolutionRepository solutionRepository;

    @RequestMapping(method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<Solution>> getSolutions(
            @RequestParam(value = "user", required = false) Long user,
            @RequestParam(value = "problem", required = false) Long problem){

        Iterable<Solution> solutions = null;

        if(user != null || problem != null){
            solutions = solutionRepository.findByProblemIdOrOwnerUserId(problem, user);
        }else{
            solutions = solutionRepository.findAll();
        }

        return new ResponseEntity<>(solutions, HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Solution> createSolution(@RequestBody Solution solution){

        Solution s = solutionRepository.save(solution);

        if(s != null){
            return new ResponseEntity<>(solution, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
