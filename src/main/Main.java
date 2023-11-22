package main;

import model.Publisher;
import view.Login;
import view.addItem;


public class Main {
    static Publisher publisher = new Publisher ("a","a",10);
    public static void main(String[] args) {
        new addItem(publisher);
    }
}
