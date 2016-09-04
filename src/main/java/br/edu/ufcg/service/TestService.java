package br.edu.ufcg.service;

import br.edu.ufcg.domain.Test;
import br.edu.ufcg.domain.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public List<Test> findByProblemId(Long problemId){
        return testRepository.findByProblemId(problemId);
    }

    public Test findOneByProblemIdAndId(Long problemId, Long testId){
        return testRepository.findOneByProblemIdAndId(problemId, testId);
    }

    public boolean exists(Long id){
        return testRepository.exists(id);
    }

    public void delete(Long id){
        testRepository.delete(id);
    }

    public Test update(Long id, Test test){
        Test t = testRepository.findOne(id);
        if(t != null){
            t = test;
        }
        return testRepository.save(t);
    }

    public Test save(Test test){
        return testRepository.save(test);
    }
}
