package br.edu.ufcg.repositories;

import br.edu.ufcg.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
