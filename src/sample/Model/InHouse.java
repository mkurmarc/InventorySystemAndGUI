package sample.Model;

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */

public class InHouse extends Part {
    private int machineId;

    public InHouse(int idPart, String namePart, double pricePart, int stockPart, int minPart, int maxPart,
                   int machineId) {
        super(idPart, namePart, pricePart, stockPart, minPart, maxPart);
        setMachineId(machineId);
    }

    // Method below validates inHouse parts and prints error message if not valid
    public static boolean validInHousePart(String name, double price, int stock, int min, int max, int machID,
                                              String errorMessage) {
        if (!name.equals("") && price != 0 && stock >= 1 && min < max && stock <= max && stock >= min
                && machID != 0) {
            return true;
        }
        else {
            errorMessage = partInputErrorMessageInHouse(name, price, stock, min, max, machID, errorMessage);
            System.out.println(errorMessage);
            return false;
        }
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }


}
