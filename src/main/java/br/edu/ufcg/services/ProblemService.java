package br.edu.ufcg.services;


import br.edu.ufcg.models.Problem;

import java.util.List;

public interface ProblemService {


    List<Problem> findAll();
    List<Problem> findByOwner(Long userId);
    Problem getProblem(Long id);

}
