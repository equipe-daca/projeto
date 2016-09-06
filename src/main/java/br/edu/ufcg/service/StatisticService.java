package br.edu.ufcg.service;

import br.edu.ufcg.domain.ProblemRepository;
import br.edu.ufcg.domain.SolutionRepository;
import br.edu.ufcg.domain.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    SolutionRepository solutionRepository;

    public Statistic getStatistic(){
        Statistic statistic = new Statistic();
        statistic.setProblems(problemRepository.count());
        statistic.setSubmitters(solutionRepository.count());
        return statistic;
    }
}
