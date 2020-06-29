package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Part;
import sample.Model.Product;

import java.util.Observable;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static int partIdTally;
    private static int productIdTally;

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
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

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> queryPartList = null;

        for(int i=0; i < (allParts.size()); i++) {
                if (allParts.get(i).getNamePart().equals(partName)) {
                    queryPartList.add();
                    break;
                }
        }
        return queryPartList;
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
