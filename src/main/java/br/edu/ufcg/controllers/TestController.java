package br.edu.ufcg.controllers;

import br.edu.ufcg.models.Test;
import br.edu.ufcg.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/problem/{problemCode}/test", produces="application/json")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity< List<Test>> getTests(@PathVariable Long problemCode){

        List<Test> tests = testService.findByProblemId(problemCode);

        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @RequestMapping(value="/{testCode}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Test> getTest(@PathVariable Long problemCode, @PathVariable Long testCode) {
        Test test = new Test();
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Test> createTest(@RequestBody Test test){
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @RequestMapping(value="/{testCode}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Test> updateTest(@PathVariable Long problemCode, @PathVariable Long testCode, @RequestBody Test test){
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @RequestMapping(value="/{testCode}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Test> deleteTest(@PathVariable Long problemCode, @PathVariable Long testCode, @RequestBody Test test){
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
