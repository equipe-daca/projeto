package br.edu.ufcg.controllers;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/problem/{problemCode}/test", produces="application/json")
public class TestController {

    @RequestMapping(value="", method=RequestMethod.GET)
    public ResponseEntity< List<Test>> getTests(){
        List<Test> tests = Collections.emptyList();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @RequestMapping(value="/{testCode}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Test> getTest(@PathVariable Long problemCode, @PathVariable Long testCode) {
        Test test = new Test("Nome", "Dica", "in", "out", true);
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
