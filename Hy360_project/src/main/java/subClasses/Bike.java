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
public class Bike extends Vehicle {

    int bike_id;

    public Bike(int vehicle_id, String color, String model, long rentingCost, String type, String brand, int isRented, int under_service, int bike_id) {
        super(vehicle_id, color, model, rentingCost, type, brand, isRented, under_service);
        this.bike_id = bike_id;
    }

    public void setBike_id(int scooter_id) {
        this.bike_id = bike_id;
    }

    public int getBike_id() {
        return bike_id;
    }

    @Override
    public String printString(int num) {
        return "            <b>Bike<b> " + num + "<br><br>"
                + "brand       = " + this.getBrand() + "<br>"
                + "model       = " + this.getModel() + "<br>"
                + "color       = " + this.getColor() + "<br>"
                + "rentingCost = " + this.getRentingCost() + "<br>"
                + "type        = " + this.getType() + "<br>"
                + "bike id     = " + this.getBike_id() + "<br>";
    }
}
