package br.edu.ufcg.models;

import br.edu.ufcg.UserClass;

public class User {

    private long id;
    private String email, password;
    private UserClass userClass;
    private transient int solvedProblems;

    public User() {
    }

    public User(long id, String email, String password, UserClass userClass, int solvedProblems) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userClass = userClass;
        this.solvedProblems = solvedProblems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public UserClass getUserClass() {
        return userClass;
    }

    public void setUserClass(UserClass userClass) {
        this.userClass = userClass;
    }

    public int getSolvedProblems() {
        return solvedProblems;
    }

    public void setSolvedProblems(int solvedProblems) {
        this.solvedProblems = solvedProblems;
    }
}
