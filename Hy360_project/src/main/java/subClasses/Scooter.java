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


    public Scooter(int vehicle_id, String color, String model, long rentingCost, String type, String brand, int isRented, int scooter_id) {
        super(vehicle_id, color, model, rentingCost, type, brand, isRented);
        this.scooter_id = scooter_id;
    }

    public void setScooter_id(int scooter_id) {
        this.scooter_id = scooter_id;
    }

    public int getScooter_id() {
        return scooter_id;
    }
}
