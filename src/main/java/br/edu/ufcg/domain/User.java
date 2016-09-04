package br.edu.ufcg.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class User implements Serializable{

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

    public User() {}

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


    public void update(User user){
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setUserClass(user.getUserClass());
    }
}
