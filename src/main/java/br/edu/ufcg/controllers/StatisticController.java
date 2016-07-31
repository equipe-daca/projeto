package br.edu.ufcg.controllers;

import br.edu.ufcg.models.Statistic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/statistic", produces="application/json")
public class StatisticController {

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Statistic> getStatistic(){
        Statistic statistic = new Statistic(99, 25);
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Statistic> updateStatistic(@RequestBody Statistic statistic){
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }
}
