package br.edu.ufcg.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Problem extends ResourceSupport implements Serializable {

    @Id
    @GeneratedValue
    private Long problemId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String desc;

    @Column
    private String tip;

    @JsonIgnore
    @OneToMany(mappedBy = "problem", fetch=FetchType.EAGER, cascade = CascadeType.ALL) //, orphanRemoval = true
    private Set<Test> tests;

    @JsonIgnore
    @ManyToOne
    private User owner;

    public Problem() {
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    public void update(Problem p){
        this.setName(p.getName());
        this.setTip(p.getTip());
        this.setDesc(p.getDesc());
        this.setOwner(p.getOwner());
        this.setTests(p.getTests());
    }

}
