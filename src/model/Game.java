package model;

import java.util.ArrayList;

public class Game extends Item{
    private ArrayList<DLC> DLC;

    public Game(){
        
    }

    public Game(int itemID, String name, String description, double price, int discount, ArrayList<Review> reviews, ItemStatus status, ArrayList<model.DLC> DLC) {
        super(itemID, name, description, price, discount, reviews, status);
        this.DLC = DLC;
    }

    public ArrayList<model.DLC> getDLC() {
        return DLC;
    }

    public void setDLC(ArrayList<model.DLC> DLC) {
        this.DLC = DLC;
    }
}
