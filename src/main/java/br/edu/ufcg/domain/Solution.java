package br.edu.ufcg.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Solution implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String body;

    @ElementCollection
    private Set<Response> responses;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Problem problem;

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

    public Set<Response> getResponses() {
        return responses;
    }

    public void setResponses(Set<Response> responses) {
        this.responses = responses;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
