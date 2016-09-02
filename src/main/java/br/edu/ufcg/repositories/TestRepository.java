package br.edu.ufcg.repositories;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.Test;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestRepository extends CrudRepository<Test, Long> {
    List<Test> findAll();
    List<Test> findByProblemId(Long problemId);
}
