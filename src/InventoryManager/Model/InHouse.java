package InventoryManager.Model;

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

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }
}

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */