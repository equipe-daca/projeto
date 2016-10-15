package br.edu.ufcg.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Test extends ResourceSupport implements Serializable {

    @Id
    @GeneratedValue
    private Long testId;

    @Column(nullable = false)
    private String name;

    @Column
    private String tip;

    @Column(nullable = false)
    private String input;

    @Column(nullable = false)
    private String output;

    @Column
    private boolean publicTest;

    @ManyToOne
    @JsonIgnore
    private Problem problem;

    public Test() {
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean isPublicTest() {
        return publicTest;
    }

    public void setPublicTest(boolean publicTest) {
        this.publicTest = publicTest;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
