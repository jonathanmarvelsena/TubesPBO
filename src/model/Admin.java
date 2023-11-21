package model;

public class Admin extends Account{
    public Admin(String name, String password, AccountStatus status) {
        super(name, password, AccountStatus.NOT_BANNED);
    }
}
