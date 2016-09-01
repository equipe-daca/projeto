package br.edu.ufcg;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.User;
import br.edu.ufcg.repositories.ProblemRepository;
import br.edu.ufcg.repositories.UserRepository;
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

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProblemTest {
    @Value("${local.server.port}")
    private int port;
    private Gson gson;
    private ProblemRepository problemRepository;
    private UserRepository userRepository;
    private User user1, user2;
    private Problem problem1, problem2;

    @Autowired
    public void setProductRepository(ProblemRepository problemRepo) {
        this.problemRepository = problemRepo;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        user1 = new User("user1@mail.com", "123456", User.Class.NORMAL);
        user2 = new User("user1@mail.com", "123456", User.Class.ADMIN);
        problem1 = new Problem("name1", "desc1", "tip1");
        problem1.setOwner(user1);
        problem2 = new Problem("name2", "desc2", "tip2");
        problem2.setOwner(user2);
    }

    @After
    public void tearDown() throws Exception {
        problemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void getProblem() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", problem1.getId())
        .when()
            .port(this.port)
            .get("/problem/{code}")
        .then().assertThat()
            .statusCode(is(200))
            .body("", not(empty()))
            .body("id", equalTo(1))
            .body("name", equalTo("name1"))
            .body("desc", equalTo("desc1"))
            .body("tip", equalTo("tip1"))
            .body("owner.id", equalTo(user1.getId().intValue()))
            .body("tests", empty());
    }


    @Test
    public void getProblems() throws Exception {

        List<Problem> list = problemRepository.findAll();

        given()
            .param("sort", false)
            .param("page", false)
            .param("user", "123412341234")
        .when()
            .port(this.port)
            .get("/problem")
        .then().assertThat()
            .statusCode(is(200))
            .body("find{it.id==1}.name",equalTo("name1"))
            .body("find{it.id==1}.desc",equalTo("desc1"))
            .body("find{it.id==1}.tip",equalTo("tip1"))
            .body("", hasSize(5));
    }

    @Test
    public void createProblem() throws Exception {
        Problem problem = new Problem("Name", "Desc", "Tip");

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(problem))
        .when()
                .port(this.port)
                .post("/problem")
        .then().assertThat()
                .statusCode(is(200));
    }

    @Test
    public void updateProblem() throws Exception {
        Problem problem = new Problem("Name", "Desc", "Tip");

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(problem))
        .when()
                .port(this.port)
                .put("/problem/1")
        .then().assertThat()
                .statusCode(is(200));
    }

    @Test
    public void deleteProblem() throws Exception {
        Problem problem = new Problem("Name", "Desc", "Tip");

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(problem))
        .when()
                .port(this.port)
                .delete("/problem/1")
        .then().assertThat()
                .statusCode(is(200));
    }
}
