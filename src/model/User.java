package model;

import java.util.ArrayList;
import controller.Controller;

public class User extends Account {
    private double wallet;
    private ArrayList<Game> ownedGames;
    private ArrayList<DLC> ownedDLCs;
    private ArrayList<ShoppingCart> cart = new ArrayList<>();
    // shopping cart is stored locally, will be emptied if
    // user logs out or exits the program

    private NotificationService notificationService;

    public User() {

    }

    public User(String name, String password, int id, String email, String phoneNumber,
            NotificationService notificationService) {
        super(name, password, id, email, phoneNumber);
        this.notificationService = notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void notifyUser(String message) {
        notificationService.sendNotification(message);
    }

    public double getWallet() {
        this.wallet = new Controller().getWallet(getId());
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Game> getOwnedGames() {
        return ownedGames;
    }

    public void setOwnedGames(ArrayList<Game> ownedGames) {
        this.ownedGames = ownedGames;
    }

    public ArrayList<DLC> getOwnedDLCs() {
        return ownedDLCs;
    }

    public void setOwnedDLCs(ArrayList<DLC> ownedDLCs) {
        this.ownedDLCs = ownedDLCs;
    }

    public ArrayList<ShoppingCart> getCart() {
        return cart;
    }

    public void setCart(ArrayList<ShoppingCart> cart) {
        this.cart = cart;
    }
}
