package br.edu.ufcg.models;

import java.io.Serializable;
import java.util.List;

public class Problem implements Serializable {

    private long code;
    private String name, desc, tip;
    private List<Test> tests;

    public Problem() {
    }

    public Problem(long code, String name, String desc, String tip, List<Test> tests) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.tip = tip;
        this.tests = tests;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
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
