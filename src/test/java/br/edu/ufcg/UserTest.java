package br.edu.ufcg;

import br.edu.ufcg.models.User;
import br.edu.ufcg.repositories.UserRepository;
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

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

    @Value("${local.server.port}")
    private int port;
    private Gson gson;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
    }

    @Test
    public void getUser() throws Exception {

        User user = new User();
        user.setEmail("user@mail.com");
        user.setPassword("123456");
        user.setUserClass(User.Class.NORMAL);

        userRepository.save(user);

        given()
                .contentType(ContentType.JSON)
                .pathParam("code", 1)
        .when()
                .port(this.port)
                .get("/user/{code}")
        .then().assertThat()
                .body("email", equalTo("user@mail.com"))
                .body("password", equalTo("123456"))
                .statusCode(is(200));
    }

    @Test
    public void createUser() throws Exception {

        User user = new User();
        user.setEmail("user@mail.com");
        user.setPassword("123456");
        user.setUserClass(User.Class.NORMAL);

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(user))
        .when()
                .port(this.port)
                .post("/user")
        .then().assertThat()
                .body("email", equalTo("user@mail.com"))
                .body("password", equalTo("123456"))
                .statusCode(is(200));
    }

    @Test
    public void updateUser() throws Exception {

        User user = new User();
        user.setEmail("user@mail.com");
        user.setPassword("123456");
        user.setUserClass(User.Class.NORMAL);

        userRepository.save(user);

        user.setEmail("newUser@mail.com");

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", 1)
            .body(gson.toJson(user))
        .when()
            .port(this.port)
            .put("/user/{code}")
        .then().assertThat()
            .body("email", equalTo("newUser@mail.com"))
            .body("password", equalTo("123456"))
            .statusCode(is(200));
    }

    @Test
    public void deleteUser() throws Exception {

        User user = new User();
        user.setEmail("user@mail.com");
        user.setPassword("123456");
        user.setUserClass(User.Class.NORMAL);

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", 1)
        .when()
            .port(this.port)
            .delete("/user/{code}")
        .then().assertThat()
            .statusCode(is(404));

        userRepository.save(user);

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", 1)
        .when()
            .port(this.port)
            .delete("/user/{code}")
        .then().assertThat()
            .statusCode(is(200));

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", 1)
        .when()
            .port(this.port)
            .delete("/user/{code}")
        .then().assertThat()
            .statusCode(is(404));
    }
}
