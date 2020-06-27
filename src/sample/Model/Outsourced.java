package sample.Model;

public class Outsourced extends Part{
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {

//        this.idPart = id;
//        this.namePart = namePart;
//        this.pricePart = pricePart;
//        this.stockPart = stockPart;
//        this.minPart = minPart;
//        this.maxPart = maxPart;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
