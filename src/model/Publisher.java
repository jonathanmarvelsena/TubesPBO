package model;

import java.util.ArrayList;

public class Publisher extends Account {

    public Publisher() {

    }

    private ArrayList<Game> publishedGames;
    private ArrayList<DLC> publishedDLCs;

    public Publisher(String name, String password, int id, String email, String phoneNumber) {
        super(name, password, id, email, phoneNumber);
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
