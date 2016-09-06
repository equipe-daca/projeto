package br.edu.ufcg.domain;

import java.io.Serializable;

public class Statistic implements Serializable {

    private Long id;

    private long problems;

    private long submitters;

    public Statistic() {
    }

    public long getProblems() {
        return problems;
    }

    public void setProblems(long problems) {
        this.problems = problems;
    }

    public long getSubmitters() {
        return submitters;
    }

    public void setSubmitters(long submitters) {
        this.submitters = submitters;
    }
}
