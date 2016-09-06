package br.edu.ufcg;

import br.edu.ufcg.domain.*;
import com.google.gson.Gson;
import io.restassured.RestAssured;
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

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class SolutionTestSuite {

    @Value("${local.server.port}")
    private int port;
    private Gson gson;
    private User user1, user2;
    private Problem problem1, problem2;
    private Solution solution1, solution2;
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

        User admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setPassword("123456");
        admin.setUserClass(User.Class.ADMIN);

        userRepository.save(admin);

        RestAssured.authentication = basic(admin.getEmail(), admin.getPassword());

        user1 = new User();
        user1.setEmail("user1@mail.com");
        user1.setPassword("123456");
        user1.setUserClass(User.Class.NORMAL);

        user2 = new User();
        user2.setEmail("user2@mail.com");
        user2.setPassword("123456");
        user2.setUserClass(User.Class.ADMIN);

        userRepository.save(user1);
        userRepository.save(user2);

        problem1 = new Problem();
        problem1.setName("name1");
        problem1.setDesc("desc1");
        problem1.setTip("tip1");
        problem1.setOwner(user1);

        problem2 = new Problem();
        problem2.setName("name2");
        problem2.setDesc("desc2");
        problem2.setTip("tip2");
        problem2.setOwner(user2);

        problemRepository.save(problem1);
        problemRepository.save(problem2);

        Set<Response> responses = new HashSet<>();

        for(int i = 1; i <= 5; i++){
            responses.add(new Response("input"+i, "output"+i));
        }

        solution1 = new Solution();
        solution1.setBody("solution1");
        solution1.setResponses(responses);
        solution1.setOwner(user1);
        solution1.setProblem(problem1);

        solution2 = new Solution();
        solution2.setBody("solution2");
        solution2.setResponses(responses);
        solution2.setOwner(user2);
        solution2.setProblem(problem2);
    }

    @After
    public void tearDown() throws Exception {
        solutionRepository.deleteAll();
        problemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void createSolution() throws Exception {

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

        solutionRepository.save(solution1);
        solutionRepository.save(solution2);

        given()
                .contentType(ContentType.JSON)
        .when()
                .port(this.port)
                .get("/solution")
        .then().assertThat()
                .statusCode(is(200))
                .body("body" , hasSize(2));

    }

    @Test
    public void getSolutionsByUser() throws Exception {

        solutionRepository.save(solution1);
        solutionRepository.save(solution2);

        given()
                .contentType(ContentType.JSON)
                .queryParam("user", user1.getId())
        .when()
                .port(this.port)
                .get("/solution")
        .then().assertThat()
                .statusCode(is(200))
                .body("body" , hasSize(1));

    }

    @Test
    public void getSolutionsByProblems() throws Exception {

        solutionRepository.save(solution1);
        solutionRepository.save(solution2);

        given()
                .contentType(ContentType.JSON)
                .queryParam("problem", problem1.getId())
                .when()
                .port(this.port)
                .get("/solution")
                .then().assertThat()
                .statusCode(is(200))
                .body("body" , hasSize(1));
    }
}
