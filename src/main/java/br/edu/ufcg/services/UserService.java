package br.edu.ufcg.services;


import br.edu.ufcg.models.User;

public interface UserService {

    User save(User user);
    User get(Long id);
    User update(Long id, User user);
    void delete(Long id);
    boolean exists(Long id);
}
