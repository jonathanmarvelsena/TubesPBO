package model;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Game> games;
    private ArrayList<DLC> dlc;

    public ShoppingCart(ArrayList<Game> games, ArrayList<DLC> dlc) {
        this.games = games;
        this.dlc = dlc;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<DLC> getDlc() {
        return dlc;
    }

    public void setDlc(ArrayList<DLC> dlc) {
        this.dlc = dlc;
    }
}
