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

    int scooter_id;


    public Scooter(int vehicle_id, String color, String model, long rentingCost, String type, String brand, int isRented, int under_service, int scooter_id, long licenseNumber) {
        super(vehicle_id, color, model, rentingCost, type, brand, isRented, under_service, licenseNumber);
        this.scooter_id = scooter_id;
    }

    public void setScooter_id(int scooter_id) {
        this.scooter_id = scooter_id;
    }

    public int getScooter_id() {
        return scooter_id;
    }

    public String printString(int num) {
        return "      <b>Scooter<b> " + num + "<br><br>"
                + "brand       = " + this.getBrand() + "<br>"
                + "color       = " + this.getColor() + "<br>"
                + "model       = " + this.getModel() + "<br>"
                + "rentingCost = " + this.getRentingCost() + "<br>"
                + "type        = " + this.getType() + "<br>"
                + "scooter id  = " + this.getScooter_id() + "<br>";
    }

}
