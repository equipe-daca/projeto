package br.edu.ufcg;

import br.edu.ufcg.domain.User;
import br.edu.ufcg.domain.UserRepository;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminUserTest {

    @Value("${local.server.port}")
    private int port;
    private Gson gson;
    private UserRepository userRepository;
    private User user1, user2, admin;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before
    public void setUp() throws Exception {
        gson = new Gson();

        admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setPassword("123456");
        admin.setUserClass(User.Class.ADMIN);

        user1 = new User();
        user1.setEmail("user1@mail.com");
        user1.setPassword("123456");
        user1.setUserClass(User.Class.NORMAL);

        user2 = new User();
        user2.setEmail("user2@mail.com");
        user2.setPassword("123456");
        user2.setUserClass(User.Class.ADMIN);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(admin);

        RestAssured.authentication = basic(admin.getEmail(), admin.getPassword());
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void getUserListWithElements() throws Exception {

        Assert.assertEquals(3, userRepository.count());

        given()
                .contentType(ContentType.JSON)
                .when()
                .port(this.port)
                .get("/user")
                .then().assertThat()
                .body("", hasSize(3))
                .statusCode(is(200));

        Assert.assertEquals(3, userRepository.count());
    }


    @Test
    public void getUserWithValidId() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .pathParam("code", user1.getId())
                .when()
                .port(this.port)
                .get("/user/{code}")
                .then().assertThat()
                .body("email", equalTo("user1@mail.com"))
                .body("password", equalTo("123456"))
                .body("userClass", equalTo("NORMAL"))
                .statusCode(is(200));
    }

    @Test
    public void getUserWithAdminClass() throws Exception {

        userRepository.save(user2);

        given()
                .contentType(ContentType.JSON)
                .pathParam("code", user2.getId())
                .when()
                .port(this.port)
                .get("/user/{code}")
                .then().assertThat()
                .body("email", equalTo("user2@mail.com"))
                .body("password", equalTo("123456"))
                .body("userClass", equalTo("ADMIN"))
                .statusCode(is(200));
    }

    @Test
    public void getUserWithInvalidId() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .pathParam("code", 999)
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
                .body("userClass", equalTo("NORMAL"))
                .statusCode(is(200));
    }

    @Test
    public void updateUser() throws Exception {

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
                .body("userClass", equalTo("NORMAL"))
                .statusCode(is(200));
    }

    @Test
    public void deleteInexistentUser() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .pathParam("code", 999)
                .when()
                .port(this.port)
                .delete("/user/{code}")
                .then().assertThat()
                .statusCode(is(404));

    }

    @Test
    public void deleteUser() throws Exception {

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
                .pathParam("code", user1.getId())
                .when()
                .port(this.port)
                .delete("/user/{code}")
                .then().assertThat()
                .statusCode(is(404));
    }
}
