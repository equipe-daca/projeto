package br.edu.ufcg.web;

import br.edu.ufcg.domain.Problem;
import br.edu.ufcg.domain.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/problem", produces="application/json")
public class ProblemController {

    @Autowired
    ProblemRepository problemRepository;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Iterable<Problem>> getProblems(@RequestParam(value = "user", required = false) Long user, Pageable pageable){
        Iterable<Problem> problems;

        if(user == null){
            problems = problemRepository.findAll(pageable);
        }else{
            problems = problemRepository.findByOwnerUserId(user, pageable);
        }

        for (Problem p : problems) updateProblemResourcesWithLinks(p, pageable);

        return new ResponseEntity<>(problems, HttpStatus.OK);
    }

    @RequestMapping(value="/{problemCode}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Problem> getProblem(@PathVariable Long problemCode){
        Problem  problem = problemRepository.findOne(problemCode);
        updateProblemResourcesWithLinks(problem, null);
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem){
        Problem p = problemRepository.save(problem);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{problemCode}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Problem> updateProblem(@PathVariable Long problemCode, @RequestBody Problem problem){
        Problem p = problemRepository.save(problem);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @RequestMapping(value="/{problemCode}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Problem> deleteProblem(@PathVariable Long problemCode){
        if(problemRepository.exists(problemCode)){
            problemRepository.delete(problemCode);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void updateProblemResourcesWithLinks(Problem problem, Pageable pageable){
        problem.add(linkTo(methodOn(ProblemController.class).getProblems(null, pageable)).slash(problem.getProblemId()).withSelfRel());
        problem.add(linkTo(methodOn(UserController.class).getUser(problem.getOwner().getUserId())).withRel("owner"));
        problem.add(linkTo(methodOn(TestController.class).getTests(problem.getProblemId(), null)).withRel("tests"));
    }
}
