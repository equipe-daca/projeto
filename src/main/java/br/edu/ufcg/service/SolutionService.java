package br.edu.ufcg.service;

import br.edu.ufcg.domain.Solution;
import br.edu.ufcg.domain.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SolutionService {

    @Autowired
    SolutionRepository solutionRepository;

    public Solution save(Solution solution){
        return solutionRepository.save(solution);
    }

    public List<Solution> findByProblemIdOrOwnerId(Long problemId, Long ownerId){
        return solutionRepository.findByProblemIdOrOwnerId(problemId, ownerId);
    }

    public List<Solution> findAll(){
        return solutionRepository.findAll();
    }
}