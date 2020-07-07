package sample.Model;

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */

import com.sun.xml.internal.bind.v2.model.core.ID;

public abstract class Part {

    private int idPart;
    private String namePart;
    private double pricePart;
    private int stockPart, minPart, maxPart;

    // Constructor
    public Part(int idPart, String namePart, double pricePart, int stockPart, int minPart, int maxPart) {
        this.idPart = idPart;
        this.namePart = namePart;
        this.pricePart = pricePart;
        this.stockPart = stockPart;
        this.minPart = minPart;
        this.maxPart = maxPart;
    }

    /*
    This method compares local variable int to every part ID in the all parts list to find the highest ID. The
    highest ID + 1 is returned. 
    */

    public static int generatePartId() {
        int idToCompare = 1;
        for (int i = 0; i < Inventory.getAllParts().size(); i++) {
            if (idToCompare < Inventory.getAllParts().get(i).getIdPart()) {
                idToCompare = Inventory.getAllParts().get(i).getIdPart();
            }
        }
        idToCompare += 1;
        return idToCompare;
    }


    /*
     Setters and Getters below
     */
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

    // Method is for In-House parts only. If the part input is not valid, this method returns the appropriate
    // error message
    public static String partInputErrorMessageInHouse(String name, double price, int stock, int min, int max, int machineId, String errorMsg) {
        if (name.equals("")) {
            errorMsg = errorMsg + ("The name field is empty. ");
        }
        if (price == 0) {
            errorMsg = errorMsg + ("The price must be more than 0. Example: 12.74 ");
        }
        if (stock < 1) {
            errorMsg = errorMsg + ("The stock must greater than 0. ");
        }
        if (min > max) {
            errorMsg = errorMsg + ("The minimum must be less than the maximum. ");
        }
        if (stock > max || stock < min) {
            errorMsg = errorMsg + ("The inventory must be between the minimum and maximum values. ");
        }
        if (machineId == 0) {
            errorMsg = errorMsg + ("The machine ID field must be more than 0. ");
        }
        return errorMsg;
    }

        // This method for Outsourced parts only. If the part input is not valid, this method returns the appropriate
        // error message
        public static String partInputErrorMessageOutsourced (String name, double price, int stock, int min, int max,
                                                              String compName, String errorMsg) {
            if (name.equals("")) {
                errorMsg += ("The name field is empty. ");
            }
            if (price == 0) {
                errorMsg += ("The price must be more than 0. ");
            }
            if (stock < 1) {
                errorMsg += ("The inventory must be more than 0. ");
            }
            if (min > max) {
                errorMsg += ("The minimum must be less than the maximum. ");
            }
            if (stock > max || stock < min) {
                errorMsg += ("The inventory must be between the minimum and maximum values. ");
            }
            if (compName.equals("")) {
                errorMsg += ("The company name is empty. ");
            }
            return errorMsg;
        }
    }