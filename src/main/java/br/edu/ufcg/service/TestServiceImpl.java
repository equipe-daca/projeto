package br.edu.ufcg.service;

import br.edu.ufcg.domain.Test;
import br.edu.ufcg.domain.TestRepository;
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
