package br.edu.ufcg.controllers;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> createProblem(@RequestBody User user){
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
