package br.edu.ufcg;

import br.edu.ufcg.domain.*;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class NormalTestTest {

    @Value("${local.server.port}")
    private int port;
    private Gson gson;
    private ProblemRepository problemRepository;
    private TestRepository testRepository;
    private UserRepository userRepository;
    private Test test1, test2;
    private Problem problem1, problem2;
    private User user1, normal;

    @Autowired
    public void setProductRepository(ProblemRepository problemRepo) {
        this.problemRepository = problemRepo;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Before
    public void setUp() throws Exception {

        normal = new User();
        normal.setEmail("admin@mail.com");
        normal.setPassword("123456");
        normal.setUserClass(User.Class.NORMAL);

        userRepository.save(normal);

        RestAssured.authentication = basic(normal.getEmail(), normal.getPassword());

        gson = new Gson();

        user1 = new User();
        user1.setEmail("user1@mail.com");
        user1.setPassword("123456");
        user1.setUserClass(User.Class.NORMAL);

        problem1 = new Problem();
        problem1.setName("name1");
        problem1.setDesc("desc1");
        problem1.setTip("tip1");
        problem1.setOwner(user1);

        test1 = new Test();
        test1.setName("test1");
        test1.setTip("tip1");
        test1.setInput("input1");
        test1.setOutput("output1");
        test1.setPublicTest(true);
        test1.setProblem(problem1);

        test2 = new Test();
        test2.setName("test2");
        test2.setTip("tip2");
        test2.setInput("input2");
        test2.setOutput("output2");
        test2.setPublicTest(false);
        test2.setProblem(problem1);
    }

    @After
    public void tearDown() throws Exception {
        testRepository.deleteAll();
        problemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @org.junit.Test
    public void getTestsFromProblem() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);
        testRepository.save(test1);
        testRepository.save(test2);

        given()
                .contentType(ContentType.JSON)
                .pathParam("problemId", problem1.getProblemId().intValue())
                .when()
                .port(this.port)
                .get("/problem/{problemId}/test")
                .then().assertThat()
                .statusCode(is(200))
                .body("content", hasSize(2));

    }

    @org.junit.Test
    public void getSpecificTest() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);
        testRepository.save(test1);
        testRepository.save(test2);

        given()
                .contentType(ContentType.JSON)
                .pathParam("problemId", problem1.getProblemId())
                .pathParam("testId", test1.getTestId())
                .when()
                .port(this.port)
                .get("/problem/{problemId}/test/{testId}")
                .then().assertThat()
                .statusCode(is(200))
                .body("testId", equalTo(test1.getTestId().intValue()))
                .body("name", equalTo("test1"))
                .body("tip", equalTo("tip1"))
                .body("input", equalTo("input1"))
                .body("output", equalTo("output1"))
                .body("publicTest", equalTo(true));
    }

    @org.junit.Test
    public void getSpecificTestWithInvalidTestId() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);
        testRepository.save(test1);

        given()
                .contentType(ContentType.JSON)
                .pathParam("problemId", problem1.getProblemId())
                .pathParam("testId", 999)
                .when()
                .port(this.port)
                .get("/problem/{problemId}/test/{testId}")
                .then().assertThat()
                .statusCode(is(404));
    }

    @org.junit.Test
    public void getSpecificTestWithInvalidProblemId() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);
        testRepository.save(test1);

        given()
                .contentType(ContentType.JSON)
                .pathParam("problemId", 999)
                .pathParam("testId", test1.getTestId())
                .when()
                .port(this.port)
                .get("/problem/{problemId}/test/{testId}")
                .then().assertThat()
                .statusCode(is(404));
    }

    @org.junit.Test
    public void removeNonexistentTest() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);
        testRepository.save(test1);

        given()
                .contentType(ContentType.JSON)
                .pathParam("problemId", problem1.getProblemId())
                .pathParam("testId", 999)
                .when()
                .port(this.port)
                .delete("/problem/{problemId}/test/{testId}")
                .then().assertThat()
                .statusCode(is(404));
    }

    @org.junit.Test
    public void removeTestFromNonexistentProblem() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);
        testRepository.save(test1);

        given()
                .contentType(ContentType.JSON)
                .pathParam("problemId", 999)
                .pathParam("testId", test1.getTestId())
                .when()
                .port(this.port)
                .delete("/problem/{problemId}/test/{testId}")
                .then().assertThat()
                .statusCode(is(404));
    }

    @org.junit.Test
    public void removeTestFromExistentProbem() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);
        testRepository.save(test1);

        given()
                .contentType(ContentType.JSON)
                .pathParam("problemId", problem1.getProblemId())
                .pathParam("testId", test1.getTestId())
                .when()
                .port(this.port)
                .delete("/problem/{problemId}/test/{testId}")
                .then().assertThat()
                .statusCode(is(200));

    }

    @org.junit.Test
    public void updateProblem() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);
        testRepository.save(test1);

        test1.setName("test2");
        test1.setTip("tip2");
        test1.setInput("input2");
        test1.setOutput("output2");
        test1.setPublicTest(false);

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(test1))
                .pathParam("problemId", problem1.getProblemId())
                .pathParam("testId", test1.getTestId())
                .when()
                .port(this.port)
                .put("/problem/{problemId}/test/{testId}")
                .then().assertThat()
                .statusCode(is(200))
                .body("testId", equalTo(test1.getTestId().intValue()))
                .body("name", equalTo("test2"))
                .body("tip", equalTo("tip2"))
                .body("input", equalTo("input2"))
                .body("output", equalTo("output2"))
                .body("publicTest", equalTo(false));
    }

    @org.junit.Test
    public void updateNonexistentTest() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);
        testRepository.save(test1);

        test1.setName("test2");
        test1.setTip("tip2");
        test1.setInput("input2");
        test1.setOutput("output2");
        test1.setPublicTest(false);

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(test1))
                .pathParam("problemId", problem1.getProblemId())
                .pathParam("testId", 999)
                .when()
                .port(this.port)
                .put("/problem/{problemId}/test/{testId}")
                .then().assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @org.junit.Test
    public void saveTest() throws Exception {

        userRepository.save(user1);
        problemRepository.save(problem1);

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(test1))
                .pathParam("problemId", problem1.getProblemId())
                .when()
                .port(this.port)
                .post("/problem/{problemId}/test")
                .then().assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo("test1"))
                .body("tip", equalTo("tip1"))
                .body("input", equalTo("input1"))
                .body("output", equalTo("output1"))
                .body("publicTest", equalTo(true));
    }

    @org.junit.Test
    public void saveTestWithNonexistentProblem() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(test1))
                .pathParam("problemId", 999)
                .when()
                .port(this.port)
                .post("/problem/{problemId}/test")
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
