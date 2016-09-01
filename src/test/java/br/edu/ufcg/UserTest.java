package br.edu.ufcg;

import br.edu.ufcg.models.User;
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
    private User user1, user2;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        user1 = new User("user1@mail.com", "123456", User.Class.NORMAL);
        user2 = new User("user2@mail.com", "123456", User.Class.NORMAL);
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void getUserListWithOneElement() throws Exception {
        userRepository.save(user1);

        given()
            .contentType(ContentType.JSON)
        .when()
            .port(this.port)
            .get("/user")
        .then().assertThat()
            .body("", hasSize(1))
            .statusCode(is(200));
    }

    @Test
    public void getUserListWithTwoElements() throws Exception {

        userRepository.save(user1);
        userRepository.save(user2);

        given()
            .contentType(ContentType.JSON)
        .when()
            .port(this.port)
            .get("/user")
        .then().assertThat()
            .body("", hasSize(2))
            .statusCode(is(200));
    }

    @Test
    public void getUserListWithZeroElements() throws Exception {

        given()
            .contentType(ContentType.JSON)
        .when()
            .port(this.port)
            .get("/user")
        .then().assertThat()
            .statusCode(is(404));
    }

    @Test
    public void getUserWithValidId() throws Exception {
        userRepository.save(user1);

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", user1.getId())
        .when()
            .port(this.port)
            .get("/user/{code}")
        .then().assertThat()
            .body("email", equalTo("user1@mail.com"))
            .body("password", equalTo("123456"))
            .statusCode(is(200));
    }

    @Test
    public void getUserWithInvalidId() throws Exception {

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", 1)
        .when()
            .port(this.port)
            .get("/user/{code}")
        .then().assertThat()
            .statusCode(is(404));
    }

    @Test
    public void createUser() throws Exception {

        given()
            .contentType(ContentType.JSON)
            .body(gson.toJson(user1))
        .when()
            .port(this.port)
            .post("/user")
        .then().assertThat()
            .body("email", equalTo("user1@mail.com"))
            .body("password", equalTo("123456"))
            .statusCode(is(200));
    }

    @Test
    public void updateUser() throws Exception {

        userRepository.save(user1);

        user1.setEmail("newUser@mail.com");

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", user1.getId())
            .body(gson.toJson(user1))
        .when()
            .port(this.port)
            .put("/user/{code}")
        .then().assertThat()
            .body("email", equalTo("newUser@mail.com"))
            .body("password", equalTo("123456"))
            .statusCode(is(200));
    }

    @Test
    public void deleteInexistentUser() throws Exception {

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", 1)
        .when()
            .port(this.port)
            .delete("/user/{code}")
        .then().assertThat()
            .statusCode(is(404));

    }

    @Test
    public void deleteUser() throws Exception {

        userRepository.save(user1);

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", user1.getId())
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
