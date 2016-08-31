package br.edu.ufcg.services;


import br.edu.ufcg.models.Problem;

import java.util.List;

public interface ProblemService {

    List<Problem> getProblems();
    Problem getProblem(Long id);

}
