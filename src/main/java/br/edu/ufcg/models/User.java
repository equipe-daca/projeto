package br.edu.ufcg.models;


import br.edu.ufcg.UserClass;

public class User {

    String email, password;
    UserClass userClass;

    public User() {
    }

    public User(String email, String password, UserClass userClass) {
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

    public UserClass getUserClass() {
        return userClass;
    }

    public void setUserClass(UserClass userClass) {
        this.userClass = userClass;
    }
}
