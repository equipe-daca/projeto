package br.edu.ufcg.web.v1;

import br.edu.ufcg.domain.Problem;
import br.edu.ufcg.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("problemControllerV1")
@RequestMapping(value="/v1/problem", produces="application/json")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity< List<Problem>> getProblems(
            @RequestParam(value = "user", required = false) Long user){

        List<Problem> problems;

        if(user == null){
            problems = problemService.findAll();
        }else{
            problems = problemService.findByOwnerId(user);
        }

        return new ResponseEntity<>(problems, HttpStatus.OK);
    }

    @RequestMapping(value="/{problemCode}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Problem> getProblem(@PathVariable Long problemCode){
        Problem  problem = problemService.getProblem(problemCode);
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem){
        Problem p = problemService.createProblem(problem);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value="/{problemCode}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Problem> updateProblem(@PathVariable Long problemCode, @RequestBody Problem problem){
        Problem p = problemService.updateProblem(problemCode, problem);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value="/{problemCode}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Problem> deleteProblem(@PathVariable Long problemCode){
        if(problemService.exists(problemCode)){
            problemService.delete(problemCode);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
