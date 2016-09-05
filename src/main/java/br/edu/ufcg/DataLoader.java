package br.edu.ufcg;

import br.edu.ufcg.domain.Problem;
import br.edu.ufcg.domain.ProblemRepository;
import br.edu.ufcg.domain.User;
import br.edu.ufcg.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        User admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setPassword("123456");
        admin.setUserClass(User.Class.ADMIN);

        userRepository.save(admin);
    }
}
