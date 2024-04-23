package model;

public class ShoppingCart {
    private int transactionID;
    private int itemID;
    private String description;
    private Boolean status;
    // status is used to determine which item in shoppingcart that you want to buy
    // or remove

    public Boolean isStatus() {
        return this.status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ShoppingCart(int transactionID, int itemID, String description) {
        this.transactionID = transactionID;
        this.itemID = itemID;
        this.description = description;
        this.status = Boolean.TRUE;
    }

    public ShoppingCart() {
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
}
