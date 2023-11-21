package model;

import java.util.ArrayList;

public class User extends Account{
    private double wallet;
    private ArrayList<Game> ownedGames;
    private ArrayList<DLC> ownedDLCs;
    private ArrayList<Transaction> history;
    private ShoppingCart cart;

    public User(String name, String password, AccountStatus status, double wallet, ArrayList<Game> ownedGames, ArrayList<DLC> ownedDLCs, ArrayList<Transaction> history, ShoppingCart cart) {
        super(name, password, status);
        this.wallet = wallet;
        this.ownedGames = ownedGames;
        this.ownedDLCs = ownedDLCs;
        this.history = history;
        this.cart = cart;
    }

    public double getWallet() {
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

    public ArrayList<Transaction> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Transaction> history) {
        this.history = history;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
}
