package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Part;
import sample.Model.Product;

import java.util.Observable;

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    public static void addPart(Part newPart) {
        if (newPart != null) {
            allParts.add(newPart);
        }
    }

    public static void addProduct(Product newProduct) {
        if (newProduct != null) {
            allProducts.add(newProduct);
        }
    }

    public static Part lookupPart(int partId) {
//        boolean isFound = false;
        Part checkPart = null;
        int checkPartID;
        for(int i=0; i < (allParts.size()); i++) {
            checkPart = allParts.get(i);
            checkPartID = checkPart.getIdPart();
            if(checkPartID == partId) {
//                isFound = true;
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

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> queryPartList = FXCollections.observableArrayList();

        if(partName.length() < 1) {
            queryPartList = allParts;
        }
        else {
            for(int i=0; i < (allParts.size()); i++) {
                if (allParts.get(i).getNamePart().toLowerCase().contains(partName.toLowerCase())) {
                    queryPartList.add(allParts.get(i));
                }
            }
        }
        return queryPartList;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> queryPartList = FXCollections.observableArrayList();

        if(productName.length() < 1) {
            queryPartList = allProducts;
        }
        else {
            for(int i=0; i < (allProducts.size()); i++) {
                if (allProducts.get(i).getName().toLowerCase().contains(productName.toLowerCase())) {
                    queryPartList.add(allProducts.get(i));
                }
            }
        }
        return queryPartList;
    }

    public static void updatePart(int index, Part newPart) {
        allParts.set(index, newPart);
    }

    public void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        if(allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteProduct(Product selectedProduct) {
        if(allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
