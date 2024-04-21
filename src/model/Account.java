package model;

public abstract class Account {
    private String name, password, email, phoneNumber;
    private AccountStatus status; // banned or not banned
    private int id;

    public Account() {

    }

    public Account(String name, String password, int id, String email, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.status = AccountStatus.NOT_BANNED;
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
