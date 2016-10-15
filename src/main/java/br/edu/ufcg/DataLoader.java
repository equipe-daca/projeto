package br.edu.ufcg;

import br.edu.ufcg.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private ProblemRepository problemRepository;
    private TestRepository testRepository;
    private SolutionRepository solutionRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setProblemRepository(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Autowired
    public void setSolutionRepository(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

//        User daca = new User();
//        daca.setEmail("daca@mail.com");
//        daca.setPassword("daca");
//        daca.setUserClass(User.Class.ADMIN);
//        userRepository.save(daca);
//
//        Problem problem1 = new Problem();
//        problem1.setName("name1");
//        problem1.setDesc("desc1");
//        problem1.setTip("tip1");
//        problem1.setOwner(daca);
//        problemRepository.save(problem1);
//
//        Test test1 = new Test();
//        test1.setName("test1");
//        test1.setTip("tip1");
//        test1.setInput("input1");
//        test1.setOutput("output1");
//        test1.setPublicTest(true);
//        test1.setProblem(problem1);
//        testRepository.save(test1);
//
//        Set<Response> responses = new HashSet<>();
//
//        for (int i = 1; i <= 5; i++) {
//            responses.add(new Response("input" + i, "output" + i));
//        }
//
//        Solution solution1 = new Solution();
//        solution1.setBody("solution1");
//        solution1.setResponses(responses);
//        solution1.setOwner(daca);
//        solution1.setProblem(problem1);
//
//        solutionRepository.save(solution1);

//        for(int i = 0; i < 100; i++){
//            User user = new User();
//            user.setEmail("user"+i+"@mail.com");
//            user.setPassword("daca");
//            user.setUserClass(User.Class.NORMAL);
//            userRepository.save(user);
//        }
    }
}
