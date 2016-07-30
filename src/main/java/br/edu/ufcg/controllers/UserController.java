package br.edu.ufcg.controllers;

import br.edu.ufcg.UserClass;
import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.Test;
import br.edu.ufcg.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @RequestMapping(value="/{userCode}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable long userCode){
        User user = new User(userCode, "user@email.com", "123456", UserClass.NORMAL, 0);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody User user){
        user.setId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<User> deleteUser(@PathVariable long userId, @RequestBody User user){
        user.setId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
