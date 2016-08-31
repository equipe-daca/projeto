package br.edu.ufcg.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class User implements Serializable {

    public enum Class {
        NORMAL, ADMIN;
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Class userClass;
    @OneToMany
    @MapKeyClass(Solution.class)
    private Map<Solution, Problem> solutions;
    @OneToMany(mappedBy = "owner")
    private List<Problem> problems;

    public User() {
        this.solutions = new HashMap<>();
    }

    public User(String email, String password, Class userClass) {
        this.email = email;
        this.password = password;
        this.userClass = userClass;
        this.solutions = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Class getUserClass() {
        return userClass;
    }

    public void setUserClass(Class userClass) {
        this.userClass = userClass;
    }

    public Map<Solution, Problem> getSolutions() {
        return solutions;
    }

    public void setSolutions(Map<Solution, Problem> solutions) {
        this.solutions = solutions;
    }
}
