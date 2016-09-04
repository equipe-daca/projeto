package br.edu.ufcg.service;

import br.edu.ufcg.domain.User;
import br.edu.ufcg.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User update(Long id, User user) {

        User u = userRepository.findOne(id);

        if(u != null){
            u = user;
        }

        return userRepository.save(u);
    }

    @Override
    public void delete(Long id) {
        if(userRepository.exists(id)){
            userRepository.delete(id);
        }
    }

    @Override
    public boolean exists(Long id) {
        return userRepository.exists(id);
    }
}
