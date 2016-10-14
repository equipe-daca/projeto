package br.edu.ufcg.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProblemRepository extends PagingAndSortingRepository<Problem, Long> {
    Page<Problem> findByOwnerUserId(Long ownerUserId, Pageable pageable);
    Iterable<Problem> findByOwnerUserId(Long ownerUserId, Sort sort);
}
