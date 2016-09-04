package br.edu.ufcg;

import br.edu.ufcg.domain.*;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class SolutionTestSuite {

    @Value("${local.server.port}")
    private int port;
    private Gson gson;
    private User user1;
    private Problem problem1;
    private Solution solution1;
    private ProblemRepository problemRepository;
    private UserRepository userRepository;
    private SolutionRepository solutionRepository;

    @Autowired
    public void setProductRepository(ProblemRepository problemRepo) {
        this.problemRepository = problemRepo;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSolutionRepository(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    @Before
    public void setUp() throws Exception {
        gson = new Gson();

        user1 = new User();
        user1.setEmail("user1@mail.com");
        user1.setPassword("123456");
        user1.setUserClass(User.Class.NORMAL);

        problem1 = new Problem();
        problem1.setName("name1");
        problem1.setDesc("desc1");
        problem1.setTip("tip1");
        problem1.setOwner(user1);

        Set<Response> responses = new HashSet<>();

        for(int i = 1; i <= 5; i++){
            responses.add(new Response("input"+i, "output"+i));
        }

        solution1 = new Solution();
        solution1.setBody("solution1");
        solution1.setResponses(responses);
        solution1.setOwner(user1);
        solution1.setProblem(problem1);
    }

    @After
    public void tearDown() throws Exception {
        solutionRepository.deleteAll();
        problemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void createSolution() throws Exception {
        userRepository.save(user1);
        problemRepository.save(problem1);

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(solution1))
        .when()
                .port(this.port)
                .post("/solution")
        .then().assertThat()
                .statusCode(is(201))
                .body("body" ,equalTo("solution1"));

    }

    @Test
    public void getSolutions() throws Exception {
        userRepository.save(user1);
        problemRepository.save(problem1);

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(solution1))
                .when()
                .port(this.port)
                .post("/solution")
                .then().assertThat()
                .statusCode(is(201))
                .body("body" ,equalTo("solution1"));

    }
}
