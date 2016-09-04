package br.edu.ufcg;

import br.edu.ufcg.domain.Statistic;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticTestSuite {

    @Value("${local.server.port}")
    private int port;
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
    }

    @Test
    public void getStatistic() throws Exception {
        given()
                .accept("application/json")
        .when()
                .port(this.port)
                .get("/statistic")
        .then().assertThat()
                .statusCode(is(200));
    }

    @Test
    public void updateStatistic() throws Exception {
        Statistic statistic =  new Statistic(99, 25);

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(statistic))
        .when()
                .port(this.port)
                .put("/statistic")
        .then().assertThat()
                .statusCode(is(200));
    }
}
