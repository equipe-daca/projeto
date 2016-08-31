package br.edu.ufcg;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.Test;
import br.edu.ufcg.repositories.ProblemRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProblemRepository problemRepo;

    private Logger log = Logger.getLogger(DataLoader.class);

    @Autowired
    public void setProductRepository(ProblemRepository problemRepo) {
        this.problemRepo = problemRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for(int i = 1; i <= 5; i++){
            Problem problem = new Problem();
            problem.setName("name" + i);
            problem.setDesc("desc" + i);
            problem.setTip("tip" + i);
            problem.setTests(Collections.emptyList());
            problemRepo.save(problem);
        }
    }
}
