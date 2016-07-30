package br.edu.ufcg;

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
public class StatisticTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void getStatistic() throws Exception {
        given()
                .accept("application/json")
                .when()
                .port(this.port)
                .get("/problem")
                .then().assertThat()
                .statusCode(is(200));
    }

    @Test
    public void updateStatistic() throws Exception {

    }
}
