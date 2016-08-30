package br.edu.ufcg;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.repositories.ProblemRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProblemControllerTest {

    private ProblemRepository problemRepository;
    private Problem problem;
    private List<br.edu.ufcg.models.Test> testes;

    @Autowired
    public void setProductRepository(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Before
    public void setUp() throws Exception {
        testes = Collections.emptyList();
        problem = new Problem("name", "description", "tip", testes);

        assertNull(problem.getId());
        assertEquals(0, problemRepository.count());

        problemRepository.save(problem);

        assertEquals(1, problemRepository.count());
        assertNotNull(problem.getId());
    }

    @After
    public void tearDown() throws Exception {
        problemRepository.deleteAll();
    }

    @Test
    public void testGetProblem(){

        Problem fetchedProblem = problemRepository.findOne(problem.getId());
        assertNotNull(fetchedProblem);
        assertEquals(problem.getId(), fetchedProblem.getId());
    }

    @Test
    public void testUpdateName(){
        Problem fetchedProblem = problemRepository.findOne(problem.getId());
        fetchedProblem.setName("new name");
        problemRepository.save(fetchedProblem);
        Problem fetchedUpdatedProblem = problemRepository.findOne(fetchedProblem.getId());
        assertEquals(fetchedProblem.getName(), fetchedUpdatedProblem.getName());
    }

}
