package br.edu.ufcg.web.v1;

import br.edu.ufcg.domain.Statistic;
import br.edu.ufcg.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("statisticControllerV1")
@RequestMapping(value="/v1/statistic", produces="application/json")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Statistic> getStatistic(){
        Statistic statistic = statisticService.getStatistic();
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }
}
