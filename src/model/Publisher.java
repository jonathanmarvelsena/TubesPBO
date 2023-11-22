package model;

import java.util.ArrayList;

public class Publisher extends User{
    private ArrayList<Game> publishedGames;
    private ArrayList<DLC> publishedDLCs;

    

    public Publisher(String name, String password, AccountStatus status, double wallet, ArrayList<Game> ownedGames,
            ArrayList<DLC> ownedDLCs, ArrayList<Transaction> history, ShoppingCart cart, ArrayList<Game> publishedGames,
            ArrayList<DLC> publishedDLCs) {
        super(name, password, AccountStatus.NOT_BANNED, wallet, ownedGames, ownedDLCs, history, cart);
        this.publishedGames = publishedGames;
        this.publishedDLCs = publishedDLCs;
    }

    public ArrayList<Game> getPublishedGames() {
        return publishedGames;
    }

    public void setPublishedGames(ArrayList<Game> publishedGames) {
        this.publishedGames = publishedGames;
    }

    public ArrayList<DLC> getPublishedDLCs() {
        return publishedDLCs;
    }

    public void setPublishedDLCs(ArrayList<DLC> publishedDLCs) {
        this.publishedDLCs = publishedDLCs;
    }
}
