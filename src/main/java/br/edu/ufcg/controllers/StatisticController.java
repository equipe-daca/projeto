package br.edu.ufcg.controllers;

import br.edu.ufcg.models.Statistic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value="/statistic")
public class StatisticController {

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Statistic>> getStatistic(){
        List<Statistic> statistics = Collections.emptyList();
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Statistic> createStatistic(@RequestBody Statistic statistic){
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Statistic> updateStatistic(@RequestBody Statistic statistic){
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }
}
