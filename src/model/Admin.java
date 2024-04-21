package model;

public class Admin extends Account {
    public Admin() {

    }

    public Admin(String name, String password, int id, String email, String phoneNumber) {
        super(name, password, id, email, phoneNumber);
    }
}
