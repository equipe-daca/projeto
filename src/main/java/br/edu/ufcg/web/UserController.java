package br.edu.ufcg.web;

import br.edu.ufcg.domain.Solution;
import br.edu.ufcg.domain.User;
import br.edu.ufcg.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/user", produces="application/json")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<User>> getUsers(){
        Iterable<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value="/{userCode}", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable long userCode){
        if(userRepository.exists(userCode)){
            User user = userRepository.findOne(userCode);
            updateUserResourcesWithLinks(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user){
        User u = userRepository.save(user);
        HttpHeaders respoHttpHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return new ResponseEntity<>(u, respoHttpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody User user){
        if(userRepository.exists(userId)){
            user.setUserId(userId);
            User u = userRepository.save(user);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/{userCode}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<User> deleteUser(@PathVariable long userCode){
        if(userRepository.exists(userCode)){
            userRepository.delete(userCode);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void updateUserResourcesWithLinks(User user){
        user.add(linkTo(methodOn(UserController.class).getUsers()).slash(user.getUserId()).withSelfRel());
        user.add(linkTo(methodOn(ProblemController.class).getProblems(user.getUserId())).withRel("problems"));
        user.add(linkTo(methodOn(SolutionController.class).getSolutions(user.getUserId(), null)).withRel("solutions"));
    }
}
