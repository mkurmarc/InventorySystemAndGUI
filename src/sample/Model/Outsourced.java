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
    // Method below validates outsourced parts and prints error message if not valid
    public static boolean validOutsourcedPart(String name, double price, int stock, int min, int max, String compName,
                                              String errorMessage) {
        if (!name.equals("") && price != 0 && stock >= 1 && min < max && stock <= max && stock >= min
                && !compName.equals("")) {
            return true;
        }
        else {
            errorMessage = partInputErrorMessageOutsourced(name, price, stock, min, max, compName, errorMessage);
            System.out.println(errorMessage);
            return false;
        }
    }
}
