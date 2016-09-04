package br.edu.ufcg.service;

import br.edu.ufcg.domain.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User save(User user);
    User get(Long id);
    User update(Long id, User user);
    void delete(Long id);
    boolean exists(Long id);
}
