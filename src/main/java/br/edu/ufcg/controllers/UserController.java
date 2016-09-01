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
        // TODO: 31/08/2016 Verificar Lógica Bizonha
        User u = userService.save(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody User user){
        // TODO: 31/08/2016 Verificar Lógica Bizonha
        User u = userService.update(userId, user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value="/{userCode}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<User> deleteUser(@PathVariable long userCode){
        // TODO: 31/08/2016 Verificar Lógica Bizonha

        if(userService.exists(userCode)){
            userService.delete(userCode);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
