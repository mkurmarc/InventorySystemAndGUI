package sample.Model;

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */

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
}
 /*
    @AUTHOR
    Marc Rios
    ID: 787989

 */