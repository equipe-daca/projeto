package br.edu.ufcg.domain;

import br.edu.ufcg.domain.Problem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
    List<Problem> findByOwnerId(Long ownerId);
    List<Problem> findAll();
}
