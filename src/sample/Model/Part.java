package sample.Model;

import javafx.collections.ObservableList;

public abstract class Part {

    private int idPart;
    private String namePart;
    private double pricePart;
    private int stockPart, minPart, maxPart;

    public Part() {
    }

    public void Part(int idPart, String namePart, double pricePart, int stockPart, int minPart, int maxPart) {
        this.idPart = idPart;
        this.namePart = namePart;
        this.pricePart = pricePart;
        this.stockPart = stockPart;
        this.minPart = minPart;
        this.maxPart = maxPart;
    }

    // Setters and Getters
    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    public String getNamePart() {
        return namePart;
    }

    public void setNamePart(String namePart) {
        this.namePart = namePart;
    }

    public double getPricePart() {
        return pricePart;
    }

    public void setPricePart(double pricePart) {
        this.pricePart = pricePart;
    }

    public int getStockPart() {
        return stockPart;
    }

    public void setStockPart(int stockPart) {
        this.stockPart = stockPart;
    }

    public int getMinPart() {
        return minPart;
    }

    public void setMinPart(int minPart) {
        this.minPart = minPart;
    }

    public int getMaxPart() {
        return maxPart;
    }

    public void setMaxPart(int maxPart) {
        this.maxPart = maxPart;
    }

    public static String validatePart(String name, double price, int stock, int min, int max, String errorMsg) {
        if (name == null) {
            errorMsg = errorMsg + ("The name field is empty. ");
        }
        if (price == 0) {
            errorMsg = errorMsg + ("The price must be more than 0. ");
        }
        if (stock < 1) {
            errorMsg = errorMsg + ("The inventory must be more than 0. ");
        }
        if (min > max) {
            errorMsg = errorMsg + ("The minimum must be less than the maximum. ");
        }
        if (stock > max || stock < min) {
            errorMsg = errorMsg + ("The inventory must be between the minimum and maximum values. ");
        }
        return errorMsg;
    }

}