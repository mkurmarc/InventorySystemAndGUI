package sample.Model;

import javafx.collections.ObservableList;

import java.util.Collection;

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */

public class Product {
    private static ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock, min, max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


    public static void addAssociatedPart(Part part) {
        if (part != null) {
            associatedParts.add(part);
        }
    }

    public static boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if(getAllAssociatedParts().contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else  {
            return false;
        }
    }

    public static ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public String getNameProduct() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }
}
