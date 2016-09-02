package br.edu.ufcg.services;

import br.edu.ufcg.models.Test;

import java.util.List;

public interface TestService {
    List<Test> findByProblemId(Long problemId);
}
