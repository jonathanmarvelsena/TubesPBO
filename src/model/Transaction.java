package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Transaction {
    private LocalTime date;
    private String description;
    private double final_price;
    private ArrayList<Item> item;

    public Transaction(LocalTime date, String description, double final_price, ArrayList<Item> item) {
        this.date = date;
        this.description = description;
        this.final_price = final_price;
        this.item = item;
    }

    public LocalTime getDate() {
        return date;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(double final_price) {
        this.final_price = final_price;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }
}
