package br.edu.ufcg.repositories;

import br.edu.ufcg.models.Problem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
    List<Problem> findAll();
}
