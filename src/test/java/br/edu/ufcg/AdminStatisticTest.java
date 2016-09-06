package br.edu.ufcg;

import br.edu.ufcg.domain.Problem;
import br.edu.ufcg.domain.ProblemRepository;
import br.edu.ufcg.domain.User;
import br.edu.ufcg.domain.UserRepository;
import com.google.gson.Gson;
import io.restassured.RestAssured;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminStatisticTest {


    @Value("${local.server.port}")
    private int port;
    UserRepository userRepository;
    ProblemRepository problemRepository;
    private Gson gson;
    private Problem problem1, problem2;
    private User admin, user1;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setProblemRepository(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Before
    public void setUp() throws Exception {
        gson = new Gson();

        admin = new User();
        admin.setEmail("admin@mail.com");
        admin.setPassword("123456");
        admin.setUserClass(User.Class.ADMIN);

        userRepository.save(admin);

        gson = new Gson();

        user1 = new User();
        user1.setEmail("user1@mail.com");
        user1.setPassword("123456");
        user1.setUserClass(User.Class.NORMAL);

        userRepository.save(user1);

        problem1 = new Problem();
        problem1.setName("name1");
        problem1.setDesc("desc1");
        problem1.setTip("tip1");
        problem1.setOwner(user1);

        problemRepository.save(problem1);

        RestAssured.authentication = basic(admin.getEmail(), admin.getPassword());
    }

    @After
    public void tearDown() throws Exception {
        problemRepository.deleteAll();
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
                .body("problems", equalTo(1))
                .body("submitters", equalTo(0))
                .statusCode(is(200));
    }
}
