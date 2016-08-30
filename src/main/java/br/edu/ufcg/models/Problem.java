package br.edu.ufcg.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Problem implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String desc;
    @Column
    private String tip;
    @ElementCollection
    @OrderColumn
    private List<Test> tests;

    public Problem() {
    }

    public Problem(String name, String desc, String tip, List<Test> tests) {
        this.name = name;
        this.desc = desc;
        this.tip = tip;
        this.tests = tests;
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
