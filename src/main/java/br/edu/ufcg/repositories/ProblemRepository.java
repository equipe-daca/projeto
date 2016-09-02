package br.edu.ufcg.repositories;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findByOwnerId(Long ownerId);
    List<Problem> findAll();
}
