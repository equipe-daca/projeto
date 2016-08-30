package br.edu.ufcg.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Embeddable
public class Test implements Serializable {

    @Column(nullable = false)
    private String name;
    @Column
    private String tip;
    @Column(nullable = false)
    private String input;
    @Column(nullable = false)
    private String output;
    @Column
    public boolean publicTest;

    public Test(String name, String tip, String input, String output, boolean publicTest) {
        this.name = name;
        this.tip = tip;
        this.input = input;
        this.output = output;
        this.publicTest = publicTest;
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
