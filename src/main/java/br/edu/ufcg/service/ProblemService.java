package br.edu.ufcg.service;


import br.edu.ufcg.domain.Problem;

import java.util.List;

public interface ProblemService {
    List<Problem> findAll();
    List<Problem> findByOwnerId(Long ownerId);
    Problem getProblem(Long id);
    Problem createProblem(Problem problem);
    Problem updateProblem(Long id, Problem problem);
    void delete(Long id);
    boolean exists(Long id);
}
