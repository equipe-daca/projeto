package br.edu.ufcg;

import br.edu.ufcg.models.User;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
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
public class UserTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void getUser() throws Exception {
        given()
                .contentType(ContentType.JSON)
                .pathParam("code", 1)
                .when()
                .port(this.port)
                .get("/user/{code}")
                .then().assertThat()
                .statusCode(is(200));
    }

    @Test
    public void createUser() throws Exception {

        Gson gson = new Gson();

        User user = new User((long) 1, "user@email", "password", UserClass.NORMAL, 0);

        String json = gson.toJson(user);

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .port(this.port)
                .post("/user")
                .then()
                .assertThat()
                .statusCode(is(200));
    }
}
