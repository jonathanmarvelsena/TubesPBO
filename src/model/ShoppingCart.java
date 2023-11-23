package model;

import java.util.ArrayList;

public class ShoppingCart {
    private int transactionID;
    private int itemID;
    private String description;
    private ArrayList<Item> items;

    

    public ShoppingCart(int transactionID, int itemID, String description, ArrayList<Item> items) {
        this.transactionID = transactionID;
        this.itemID = itemID;
        this.description = description;
        this.items = items;
    }

    
    public ShoppingCart() {
    }


    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }


    public int getTransactionID() {
        return transactionID;
    }


    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }


    public int getitemID() {
        return itemID;
    }


    public void setitemID(int itemID) {
        this.itemID = itemID;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
