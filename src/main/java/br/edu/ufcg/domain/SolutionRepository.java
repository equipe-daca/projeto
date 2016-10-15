package br.edu.ufcg.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SolutionRepository extends PagingAndSortingRepository<Solution, Long> {
    Iterable<Solution> findByProblemProblemIdOrOwnerUserId(Long problemId, Long ownerUserId, Sort sort);
    Page<Solution> findByProblemProblemIdOrOwnerUserId(Long problemId, Long ownerUserId, Pageable pageable);
    Iterable<Solution> findAll(Sort sort);
    Page<Solution> findAll(Pageable pageable);
}
