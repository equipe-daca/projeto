package br.edu.ufcg.controllers;

import br.edu.ufcg.models.User;
import br.edu.ufcg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user", produces="application/json")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/{userCode}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable long userCode){
        User user = userService.get(userCode);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user){
        // TODO: 31/08/2016 Melhora lógica
        userService.save(user);
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
