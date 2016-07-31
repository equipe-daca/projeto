package br.edu.ufcg;

import br.edu.ufcg.models.Solution;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class SolutionTest {

    @Value("${local.server.port}")
    private int port;
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
    }

    @Test
    public void createSolution() throws Exception {
        Solution solution = new Solution("Body", new HashMap<>());

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(solution))
        .when()
                .port(this.port)
                .post("/solution")
        .then().assertThat()
                .statusCode(is(200));
    }
}
