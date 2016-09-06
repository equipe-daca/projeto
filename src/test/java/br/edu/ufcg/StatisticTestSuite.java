package br.edu.ufcg;

import br.edu.ufcg.domain.Statistic;
import br.edu.ufcg.domain.User;
import br.edu.ufcg.domain.UserRepository;
import com.google.gson.Gson;
import io.restassured.RestAssured;
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

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticTestSuite {

    @Value("${local.server.port}")
    private int port;
    private Gson gson;
    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before
    public void setUp() throws Exception {
        gson = new Gson();

        User admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setPassword("123456");
        admin.setUserClass(User.Class.ADMIN);

        userRepository.save(admin);

        RestAssured.authentication = basic(admin.getEmail(), admin.getPassword());
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
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
}
