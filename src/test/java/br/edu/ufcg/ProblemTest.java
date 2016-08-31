package br.edu.ufcg;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.repositories.ProblemRepository;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
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
    private ProblemRepository problemRepo;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
    }

    @Autowired
    public void setProductRepository(ProblemRepository problemRepo) {
        this.problemRepo = problemRepo;
    }

    @Test
    public void getProblem() throws Exception {
        given()
                .accept("application/json")
        .when()
                .port(this.port)
                .get("/problem/1")
        .then().assertThat()
                .statusCode(is(200))
                .body("", not(empty()))
                .body("id",equalTo(1))
                .body("name",equalTo("name1"))
                .body("desc",equalTo("desc1"))
                .body("tip",equalTo("tip1"))
                .body("tests", empty());
    }


    @Test
    public void getProblems() throws Exception {

        List<Problem> list = problemRepo.findAll();

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
