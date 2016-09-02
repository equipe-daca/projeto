package br.edu.ufcg.services;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.Test;
import br.edu.ufcg.repositories.ProblemRepository;
import br.edu.ufcg.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    @Override
    public List<Test> findByProblemId(Long problemId) {
        return testRepository.findByProblemId(problemId);
    }
}
