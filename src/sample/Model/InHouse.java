package sample.Model;

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */

import sample.View_Controller.addPartController;

public class InHouse extends Part {
    private int machineId;

    public InHouse(int idPart, String namePart, double pricePart, int stockPart, int minPart, int maxPart,
                   int machineId) {
        super(idPart, namePart, pricePart, stockPart, minPart, maxPart);
        setMachineId(machineId);
    }

    // Method below validates inHouse parts and prints error message if not valid
    public static boolean validInHousePart(String name, double price, int stock, int min, int max, int machID) {
        boolean isCorrect = true;
        if (name.equals("")){
            isCorrect = false;
        }
        if (price == 0) {
            isCorrect = false;
        }
        if (!(stock >= 1)) {
            isCorrect = false;
        }
        if (!(min < max)) {
            isCorrect =false;
        }
        if (!(stock <= max)) {
            isCorrect = false;
        }
        if (!(stock >= min)) {
            isCorrect = false;
        }
        if (machID == 0) { // || addPartController.isInteger(machID)
            isCorrect = false;
        }
        return isCorrect;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }


}
