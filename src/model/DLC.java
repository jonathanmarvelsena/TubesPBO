package model;

import java.util.ArrayList;

//DLC stands for Downloadable Content which is extra content that can be bought for an existing game

public class DLC extends Item {

    public DLC(int itemID, String name, String type, String description, double price, int publisherID,
            ArrayList<Review> reviews) {
        super(itemID, name, "DLC", description, price, publisherID, reviews);
    }

    public DLC() {
    }

}
