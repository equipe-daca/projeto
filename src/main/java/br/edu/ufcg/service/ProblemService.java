package br.edu.ufcg.service;

import br.edu.ufcg.domain.Problem;
import br.edu.ufcg.domain.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProblemService {

    @Autowired
    ProblemRepository problemRepository;

    public List<Problem> findAll(){
        return problemRepository.findAll();
    }

    public List<Problem> findByOwnerId(Long ownerId){
        return problemRepository.findByOwnerId(ownerId);
    }

    public Problem getProblem(Long id){
        return problemRepository.findOne(id);
    }

    public Problem createProblem(Problem problem){
        return problemRepository.save(problem);
    }

    public Problem updateProblem(Long id, Problem problem){
        Problem p = problemRepository.findOne(id);
        if(p != null){
            p.update(problem);
        }
        return problemRepository.save(p);
    }

    public void delete(Long id){
        problemRepository.delete(id);
    }

    public boolean exists(Long id){
        return problemRepository.exists(id);
    }
}
