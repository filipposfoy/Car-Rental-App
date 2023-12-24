/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subClasses;

import mainClasses.Vehicle;

/**
 *
 * @author user
 */
public class Scooter extends Vehicle {

    String model;
    int totalMiles;


    public Scooter(int vehicle_id, String color, String functionality, int rentingCost, int ensuranceCost, boolean IsRented, String model, int totalMiles) {
        super(vehicle_id, color, functionality, rentingCost, ensuranceCost, IsRented);
        this.model = model;
        this.totalMiles = totalMiles;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTotalMiles(int totalMiles) {
        this.totalMiles = totalMiles;
    }

    public String getModel() {
        return model;
    }

    public int getTotalMiles() {
        return totalMiles;
    }
}
