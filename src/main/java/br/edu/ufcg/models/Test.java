package br.edu.ufcg.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
public class Test implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String tip;
    @ElementCollection
    private Map<String, String> inputs;
    @ManyToOne
    private Problem problem;

    public Test() {
    }

    public Test(String name, String tip, Map<String, String> inputs, Problem problem) {
        this.name = name;
        this.tip = tip;
        this.inputs = inputs;
        this.problem = problem;
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

    public Map<String, String> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, String> inputs) {
        this.inputs = inputs;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
