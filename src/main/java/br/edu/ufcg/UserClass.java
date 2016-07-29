package br.edu.ufcg;

public enum UserClass {
    ANONYMOUS(1), NORMAL(2), ADMIN(3);

    public int value;

    UserClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
