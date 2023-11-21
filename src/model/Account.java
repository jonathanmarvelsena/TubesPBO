package model;

public abstract class Account {
    private String name, password;
    private AccountStatus status;

    public Account(String name, String password, AccountStatus status) {
        this.name = name;
        this.password = password;
        this.status = status;
    }
}
