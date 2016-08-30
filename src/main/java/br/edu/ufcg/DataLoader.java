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
//        List<Test> testes = new ArrayList<>();
//        testes.add(new Test("nome", "dica", "in", "out", true));
//        Problem p1 = new Problem("nome", "descricao", "dica", testes);
//        problemRepo.save(p1);
    }
}
