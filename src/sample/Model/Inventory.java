package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Part;
import sample.Model.Product;

import java.util.Observable;

public class Inventory {

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public Part lookupPart(int partId) {
        Part checkPart = null;
        int checkPartID;
        for(int i=0; i < (allParts.size()); i++) {
            checkPart = allParts.get(i);
            checkPartID = checkPart.getIdPart();
            if(checkPartID == partId) {
                break;
            }
        }
        return checkPart;
    }

    public Product lookupProduct(int productId) {
        Product checkProduct = null;
        int checkProductID;
        for(int i=0; i < (allProducts.size()); i++) {
            checkProduct = allProducts.get(i);
            checkProductID = checkProduct.getId();
            if(checkProductID == productId) {
                break;
            }
        }
        return checkProduct;
    }

    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> newList = null;
        Part checkPart = null;
        String checkPartName;
        for(int i=0; i < (allParts.size()); i++) {
            checkPart = allParts.get(i);
            checkPartName = checkPart.getNamePart();
            if(checkPartName == partName) {
                newList.add(checkPart);
                break;
            }
        }
        return newList;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return null;
    }

    public void updatePart(int index, Part newPart) {
        allParts.set(index, newPart);
    }

    public void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public boolean deletePart(Part selectedPart) {
        if(allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteProduct(Product selectedProduct) {
        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}