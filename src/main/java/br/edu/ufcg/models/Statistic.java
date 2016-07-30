package br.edu.ufcg.models;

public class Statistic {

    private int problems, submitters;

    public Statistic() {
    }

    public Statistic(int problems, int submitters) {
        this.problems = problems;
        this.submitters = submitters;
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
