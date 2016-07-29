package br.edu.ufcg.models;

import java.io.Serializable;
import java.util.List;

public class Problem implements Serializable {

    Long code;
    String name, desc, tip;
    List<Test> tests;

    public Problem() {
    }

    public Problem(Long code, String name, String desc, String tip, List<Test> tests) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.tip = tip;
        this.tests = tests;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
