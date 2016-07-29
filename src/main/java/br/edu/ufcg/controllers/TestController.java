package br.edu.ufcg.controllers;

import br.edu.ufcg.models.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/problem/{problemCode}/test")
public class TestController {

    @RequestMapping(value="", method=RequestMethod.GET)
    public ResponseEntity< List<Test>> getProblems(){
        List<Test> tests = Collections.emptyList();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @RequestMapping(value="/{testCode}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Test> getProblem(@PathVariable Long problemCode, @PathVariable Long testCode){
        Map<String, String> valores = new HashMap<>();
        valores.put("entrada1", "saida1");
        valores.put("entrada2", "saida2");
        Test test = new Test("Nome", "Dica", valores);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Test> createProblem(@RequestBody Test test){
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @RequestMapping(value="/{testCode}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Test> updateProblem(@PathVariable Long problemCode, @PathVariable Long testCode, @RequestBody Test test){
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @RequestMapping(value="/{testCode}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Test> deleteProblem(@PathVariable Long problemCode, @PathVariable Long testCode, @RequestBody Test test){
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
