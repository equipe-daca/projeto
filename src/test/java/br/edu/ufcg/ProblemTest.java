package br.edu.ufcg;

import br.edu.ufcg.models.Problem;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProblemTest {
    @Value("${local.server.port}")
    private int port;
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
    }

    @Test
    public void getProblems() throws Exception {
        given()
                .param("sort", false)
                .param("page", false)
                .param("user", "123412341234")
        .when()
                .port(this.port)
                .get("/problem")
        .then().assertThat()
                .statusCode(is(200))
                .body("", empty());
    }

    @Test
    public void getProblem() throws Exception {
        given()
                .accept("application/json")
        .when()
                .port(this.port)
                .get("/problem/1")
        .then().assertThat()
                .statusCode(is(200));
    }

    @Test
    public void createProblem() throws Exception {
        Problem problem = new Problem("Name", "Desc", "Tip", new ArrayList<>());

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
        Problem problem = new Problem("Name", "Desc", "Tip", new ArrayList<>());

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
        Problem problem = new Problem("Name", "Desc", "Tip", new ArrayList<>());

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
