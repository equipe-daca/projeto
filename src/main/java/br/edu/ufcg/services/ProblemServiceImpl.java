package br.edu.ufcg.services;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemRepository problemRepo;

    @Override
    public List<Problem> getProblems() {
        return problemRepo.findAll();
    }

    @Override
    public Problem getProblem(Long id) {
        return problemRepo.findOne(id);
    }
}
