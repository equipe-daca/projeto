package br.edu.ufcg.web;

import br.edu.ufcg.domain.Solution;
import br.edu.ufcg.domain.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/solution", produces="application/json")
public class SolutionController {

    @Autowired
    SolutionRepository solutionRepository;

    @RequestMapping(method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<Solution>> getSolutions(
            @RequestParam(value = "user", required = false) Long user,
            @RequestParam(value = "problem", required = false) Long problem, Pageable pageable){

        Page<Solution> solutions = null;

        if(user != null || problem != null){
            solutions = solutionRepository.findByProblemProblemIdOrOwnerUserId(problem, user, pageable);
        }else{
            solutions = solutionRepository.findAll(pageable);
        }

        for (Solution s : solutions){
            updateSolutionResourcesWithLinks(s, pageable);
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

    private void updateSolutionResourcesWithLinks(Solution solution, Pageable pageable){
        solution.add(linkTo(methodOn(SolutionController.class).getSolutions(solution.getOwner().getUserId(), solution.getProblem().getProblemId(), pageable)).slash(solution.getSolutionId()).withSelfRel());
        solution.add(linkTo(methodOn(UserController.class).getUser(solution.getOwner().getUserId())).withRel("user"));
        solution.add(linkTo(methodOn(ProblemController.class).getProblem(solution.getProblem().getProblemId())).withRel("problem"));
    }
}
