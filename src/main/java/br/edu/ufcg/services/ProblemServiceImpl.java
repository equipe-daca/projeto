package br.edu.ufcg.services;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemRepository problemRepo;

    @Override
    public List<Problem> getProblems() {
        return problemRepo.findAll();
    }
}
