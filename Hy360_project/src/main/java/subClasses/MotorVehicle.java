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
public class MotorVehicle extends Vehicle {

    String model, type;
    int totalMiles, registrationNumber;

    public MotorVehicle(int vehicle_id, String color, String functionality, int rentingCost, int ensuranceCost, boolean IsRented, String model, String type, int totalMiles, int registrationNumber) {
        super(vehicle_id, color, functionality, rentingCost, ensuranceCost, IsRented);
        this.model = model;
        this.type = type;
        this.totalMiles = totalMiles;
        this.registrationNumber = registrationNumber;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getType() {
        return type;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

}
