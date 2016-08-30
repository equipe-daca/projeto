package br.edu.ufcg.models;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Embeddable
public class Solution implements Serializable {

    @Column
    private String body;
    @ElementCollection
    @OrderColumn
    private List<Test> tests;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    public Solution() {
        this.created = Calendar.getInstance();
        this.tests = Lists.newArrayList();
    }

    public Solution(String body) {
        this.body = body;
        this.created = Calendar.getInstance();
        this.tests = Lists.newArrayList();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }
}
