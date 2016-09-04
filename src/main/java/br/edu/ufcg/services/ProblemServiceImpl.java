package br.edu.ufcg.services;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemRepository problemRepository;

    @Override
    public List<Problem> findAll() {
        return problemRepository.findAll();
    }

    @Override
    public List<Problem> findByOwnerId(Long user) {
        return problemRepository.findByOwnerId(user);
    }

    @Override
    public Problem getProblem(Long id) {
        return problemRepository.findOne(id);
    }

    @Override
    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    @Override
    public Problem updateProblem(Long id, Problem problem) {

        Problem p = problemRepository.findOne(id);

        if(p != null){
            p = problem;
        }
        return problemRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        problemRepository.delete(id);
    }

    @Override
    public boolean exists(Long id) {
        return problemRepository.exists(id);
    }
}
