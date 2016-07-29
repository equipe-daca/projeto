package br.edu.ufcg.models;


import br.edu.ufcg.UserClass;

public class User {

    String email, password;
    int userClass;

    public User() {
    }

    public User(String email, String password, int userClass) {
        this.email = email;
        this.password = password;
        this.userClass = userClass;
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

    public int getUserClass() {
        return userClass;
    }

    public void setUserClass(int userClass) {
        this.userClass = userClass;
    }
}
