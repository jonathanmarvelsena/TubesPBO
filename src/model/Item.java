package model;

import java.util.ArrayList;

public abstract class Item {
    private int itemID;
    private String name;
    private String description;
    private double price;
    private int discountID;
    private ArrayList<Review> reviews;
    private ItemStatus status;
    private String cover;

    public Item(){

    }

    public Item(int itemID, String name, String description, double price, int discountID, ArrayList<Review> reviews, ItemStatus status) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discountID = discountID;
        this.reviews = reviews;
        this.status = status;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
