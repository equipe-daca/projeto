package br.edu.ufcg.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
    Iterable<Problem> findByOwnerUserId(Long ownerUserId);
}
