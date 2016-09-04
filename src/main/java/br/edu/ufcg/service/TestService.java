package br.edu.ufcg.service;

import br.edu.ufcg.domain.Test;

import java.util.List;

public interface TestService {
    List<Test> findByProblemId(Long problemId);
}
