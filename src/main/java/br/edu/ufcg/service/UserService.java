package br.edu.ufcg.service;

import br.edu.ufcg.domain.User;
import br.edu.ufcg.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User get(Long id){
        return userRepository.findOne(id);
    }

    public User update(Long id, User user){
        User u = userRepository.findOne(id);
        if(u != null){
            u.update(user);
        }
        return userRepository.save(u);
    }

    public void delete(Long id){
        userRepository.delete(id);
    }

    public boolean exists(Long id){
        return userRepository.exists(id);
    }
}
