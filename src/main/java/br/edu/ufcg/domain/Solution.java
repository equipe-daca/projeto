package br.edu.ufcg.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Solution implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String body;
//    @OneToMany(mappedBy = "solution")
//    @OrderColumn
//    private List<Test> tests;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    public Solution() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

//    public List<Test> getTests() {
//        return tests;
//    }
//
//    public void setTests(List<Test> tests) {
//        this.tests = tests;
//    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }
}
