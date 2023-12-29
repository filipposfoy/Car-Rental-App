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

    public Bike(int vehicle_id, String color, String model, long rentingCost, String type, String brand, int isRented, int bike_id) {
        super(vehicle_id, color, model, rentingCost, type, brand, isRented);
        this.bike_id = bike_id;
    }

    public void setBike_id(int scooter_id) {
        this.bike_id = bike_id;
    }

    public int getBike_id() {
        return bike_id;
    }
}
