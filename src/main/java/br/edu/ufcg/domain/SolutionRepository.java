package br.edu.ufcg.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolutionRepository extends CrudRepository<Solution, Long> {
    List<Solution> findAll();
    List<Solution> findByProblemIdOrOwnerId(Long problemId, Long ownerId);
}
