package br.edu.ufcg;

import br.edu.ufcg.models.Problem;
import br.edu.ufcg.models.Test;
import br.edu.ufcg.models.User;
import br.edu.ufcg.repositories.ProblemRepository;
import br.edu.ufcg.repositories.TestRepository;
import br.edu.ufcg.repositories.UserRepository;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestTestSuite {

    @Value("${local.server.port}")
    private int port;
    private Gson gson;
    private ProblemRepository problemRepository;
    private TestRepository testRepository;
    private UserRepository userRepository;
    private Test test1, test2;
    private List<Test> tests1, tests2;
    private Problem problem1, problem2;
    private User user1, user2;
    private Logger logger = Logger.getLogger(TestTestSuite.class);

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
        gson = new Gson();

        user1 = new User("user1@mail.com", "123456", User.Class.NORMAL);
        user2 = new User("user2@mail.com", "123456", User.Class.ADMIN);

        problem1 = new Problem("name1", "desc1", "tip1");
        problem2 = new Problem("name2", "desc2", "tip2");

        problem1.setOwner(user1);
        problem2.setOwner(user2);

        test1 = new Test("test1", "tip1", "input1", "output1", false);
        test2 = new Test("test2", "tip2", "input2", "output2", true);

        tests1 = new ArrayList<Test>();
        tests2 = new ArrayList<Test>();

        tests1.add(test1);
        tests2.add(test2);

        problem1.setTests(tests1);
        problem2.setTests(tests2);

        //test1.setProblem(problem1);
        //test2.setProblem(problem2);
    }

    @After
    public void tearDown() throws Exception {
        problemRepository.deleteAll();
        testRepository.deleteAll();
        userRepository.deleteAll();
    }

    @org.junit.Test
    public void getTestsFromProblem() throws Exception {

        userRepository.save(user1);
        testRepository.save(test1);
        problemRepository.save(problem1);


//        problem1.getTests().add(test1);
//        testRepository.save(test1);
//        problemRepository.save(problem1);

        logger.info("___________________________________________");
        logger.info(testRepository.findAll().size());
        logger.info(testRepository.findAll().get(0).toString());

        given()
                .contentType(ContentType.JSON)
                .pathParam("problemId", problem1.getId().intValue())
        .when()
                .port(this.port)
                .get("/problem/{problemId}/test")
        .then().assertThat()
                .statusCode(is(200))
//                .body("find{it.id=="+problem1.getId()+"}.name",equalTo("name1"))
//                .body("find{it.id=="+problem1.getId()+"}.desc",equalTo("desc1"))
//                .body("find{it.id=="+problem1.getId()+"}.tip",equalTo("tip1"))
                .body("", hasSize(1));
    }
}
