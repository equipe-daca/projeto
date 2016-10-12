package br.edu.ufcg.web;

import br.edu.ufcg.domain.ProblemRepository;
import br.edu.ufcg.domain.Test;
import br.edu.ufcg.domain.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/problem/{problemCode}/test", produces="application/json")
public class TestController {

    @Autowired
    TestRepository testRepository;

    @Autowired
    ProblemRepository problemRepository;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity< List<Test>> getTests(@PathVariable Long problemCode){
        List<Test> tests = testRepository.findByProblemId(problemCode);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @RequestMapping(value="/{testCode}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Test> getTest(@PathVariable Long problemCode, @PathVariable Long testCode) {
        Test test = testRepository.findOneByProblemIdAndId(problemCode, testCode);
        if(test != null){
            return new ResponseEntity<>(test, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Test> createTest(@PathVariable Long problemCode, @RequestBody Test test){

        if(problemRepository.exists(problemCode)){
            Test t = testRepository.save(test);
            if(t != null){
                return new ResponseEntity<>(test, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/{testCode}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Test> updateTest(@PathVariable Long problemCode, @PathVariable Long testCode, @RequestBody Test test){
        if(testRepository.exists(testCode) && problemRepository.exists(problemCode)){
            Test t = testRepository.save(test);
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/{testCode}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Test> deleteTest(@PathVariable Long problemCode, @PathVariable Long testCode){
        if(testRepository.exists(testCode) && problemRepository.exists(problemCode)){
            testRepository.delete(testCode);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
