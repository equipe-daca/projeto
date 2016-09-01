package br.edu.ufcg.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Test implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
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
    private Problem problem;
    @ManyToOne
    private Solution solution;

    public Test() {
    }

    public Test(String name, String tip, String input, String output, boolean publicTest) {
        this.name = name;
        this.tip = tip;
        this.input = input;
        this.output = output;
        this.publicTest = publicTest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
