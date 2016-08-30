package br.edu.ufcg;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.repositories.ProblemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProblemControllerTest {

    private ProblemRepository problemRepository;

    @Autowired
    public void setProductRepository(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Test
    public void testSaveProblem(){
        List<br.edu.ufcg.models.Test> testes = new ArrayList<>();
        testes.add(new br.edu.ufcg.models.Test("nome", "dica", "in", "out", true));
        Problem p1 = new Problem("nome", "descricao", "dica", testes);
        problemRepository.save(p1);
    }
}
