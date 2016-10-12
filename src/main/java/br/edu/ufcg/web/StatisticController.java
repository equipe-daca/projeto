package br.edu.ufcg.web;

import br.edu.ufcg.domain.ProblemRepository;
import br.edu.ufcg.domain.Statistic;
import br.edu.ufcg.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1/statistic", produces="application/json")
public class StatisticController {

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Statistic> getStatistic(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
