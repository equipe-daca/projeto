package br.edu.ufcg.domain;

import br.edu.ufcg.domain.Test;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestRepository extends CrudRepository<Test, Long> {
    List<Test> findAll();
    List<Test> findByProblemId(Long problemId);
    Test findOneByProblemIdAndId(Long problemId, Long testId);
}