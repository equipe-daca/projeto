package br.edu.ufcg.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Statistic implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private int problems, submitters;
    public Statistic() {
    }

    public Statistic(int problems, int submitters) {
        this.problems = problems;
        this.submitters = submitters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProblems() {
        return problems;
    }

    public void setProblems(int problems) {
        this.problems = problems;
    }

    public int getSubmitters() {
        return submitters;
    }

    public void setSubmitters(int submitters) {
        this.submitters = submitters;
    }
}
