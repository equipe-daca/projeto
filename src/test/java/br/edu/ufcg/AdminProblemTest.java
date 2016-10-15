package br.edu.ufcg;

import br.edu.ufcg.domain.Problem;
import br.edu.ufcg.domain.ProblemRepository;
import br.edu.ufcg.domain.User;
import br.edu.ufcg.domain.UserRepository;
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

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminProblemTest {
    @Value("${local.server.port}")
    private int port;
    private Gson gson;
    private ProblemRepository problemRepository;
    private UserRepository userRepository;
    private User user1, admin;
    private Problem problem1;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setProblemRepository(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Before
    public void setUp() throws Exception {

        admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setPassword("123456");
        admin.setUserClass(User.Class.ADMIN);

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

        userRepository.save(admin);
        userRepository.save(user1);

        problemRepository.save(problem1);

        RestAssured.authentication = basic(admin.getEmail(), admin.getPassword());
    }

    @After
    public void tearDown() throws Exception {
        problemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void getProblems() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .queryParam("user", user1.getId())
                .when()
                .port(this.port)
                .get("/problem")
                .then().assertThat()
                .statusCode(is(200))
                .body("content", hasSize(1));
    }

    @Test
    public void getProblem() throws Exception {

        problemRepository.save(problem1);

        given()
                .contentType(ContentType.JSON)
                .pathParam("code", problem1.getProblemId())
                .when()
                .port(this.port)
                .get("/problem/{code}")
                .then().assertThat()
                .statusCode(is(200))
                .body("", not(empty()))
                .body("problemId", equalTo(problem1.getProblemId().intValue()))
                .body("name", equalTo("name1"))
                .body("desc", equalTo("desc1"))
                .body("tip", equalTo("tip1"));
    }

    @Test
    public void createProblem() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(problem1))
                .when()
                .port(this.port)
                .post("/problem")
                .then().assertThat()
                .statusCode(is(201))
                .body("", not(empty()))
                .body("name", equalTo("name1"))
                .body("desc", equalTo("desc1"))
                .body("tip", equalTo("tip1"));
    }

    @Test
    public void updateProblem() throws Exception {

        problemRepository.save(problem1);

        problem1.setName("name2");

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(problem1))
                .pathParam("code", problem1.getProblemId())
                .when()
                .port(this.port)
                .put("/problem/{code}")
                .then().assertThat()
                .body("", not(empty()))
                .body("name", equalTo("name2"))
                .body("desc", equalTo("desc1"))
                .body("tip", equalTo("tip1"))
                .statusCode(is(200));
    }

    @Test
    public void deleteProblem() throws Exception {

        problemRepository.save(problem1);

        given()
                .contentType(ContentType.JSON)
                .pathParam("code", problem1.getProblemId())
                .when()
                .port(this.port)
                .delete("/problem/{code}")
                .then().assertThat()
                .statusCode(is(200));

        given()
                .contentType(ContentType.JSON)
                .pathParam("code", problem1.getProblemId())
                .when()
                .port(this.port)
                .delete("/problem/{code}")
                .then().assertThat()
                .statusCode(is(404));
    }

    @Test
    public void deleteInexistentProblem() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .pathParam("code", 1)
                .when()
                .port(this.port)
                .delete("/problem/{code}")
                .then().assertThat()
                .statusCode(is(404));
    }
}
