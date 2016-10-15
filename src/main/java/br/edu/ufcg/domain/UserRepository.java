package br.edu.ufcg.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{
    User findByEmail(String email);
    Iterable<User> findAll(Sort sort);
    Page<User> findAll(Pageable pageable);
}
