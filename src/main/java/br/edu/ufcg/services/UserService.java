package br.edu.ufcg.services;


import br.edu.ufcg.models.User;

public interface UserService {

    User save(User user);
    User get(Long id);
}
