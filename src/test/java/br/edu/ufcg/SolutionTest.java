package br.edu.ufcg;

import br.edu.ufcg.models.Solution;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
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

    @Test
    public void createSolution() throws Exception {
        Gson gson = new Gson();

        Solution solution = new Solution("Body", new HashMap<>());

        String json = gson.toJson(solution);

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .port(this.port)
                .post("/solution")
                .then()
                .assertThat()
                .statusCode(is(200));
    }
}
