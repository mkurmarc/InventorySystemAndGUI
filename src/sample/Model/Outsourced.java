package sample.Model;

public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int idPart, String namePart, double pricePart, int stockPart, int minPart, int maxPart, String companyName) {
        super(idPart, namePart, pricePart, stockPart, minPart, maxPart);
        setCompanyName(companyName);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
