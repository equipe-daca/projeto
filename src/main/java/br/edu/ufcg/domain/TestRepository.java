package br.edu.ufcg.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestRepository extends PagingAndSortingRepository<Test, Long> {
    Iterable<Test> findAll(Sort sort);
    Page<Test> findAll(Pageable pageable);
    Iterable<Test> findByProblemProblemId(Long problemId, Sort sort);
    Page<Test> findByProblemProblemId(Long problemId, Pageable pageable);
    Test findOneByProblemProblemIdAndTestId(Long problemId, Long testId);
}