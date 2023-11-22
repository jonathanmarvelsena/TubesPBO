package model;

import java.util.ArrayList;

public class DLC extends Item{
    public DLC(int itemID, String name, String description, double price, int discount, ArrayList<Review> reviews, ItemStatus status) {
        super(itemID, name, description, price, discount, reviews, status);
    }

    public DLC() {
    }

}
