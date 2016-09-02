package br.edu.ufcg.models;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

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
    @OneToMany(mappedBy = "problem", fetch=FetchType.EAGER) //, orphanRemoval = true, cascade = CascadeType.ALL)
    @OrderColumn
    private List<Test> tests;
    @ManyToOne
    private User owner;

    public Problem() {
        this.tests = Lists.newArrayList();
    }

    public Problem(String name, String desc, String tip) {
        this.name = name;
        this.desc = desc;
        this.tip = tip;
        this.tests = Lists.newArrayList();
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
