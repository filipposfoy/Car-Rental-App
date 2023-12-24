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

    String type;
    int size;

    public Bike(int vehicle_id, String color, String functionality, int rentingCost, int ensuranceCost, boolean IsRented, String type, int size) {
        super(vehicle_id, color, functionality, rentingCost, ensuranceCost, IsRented);
        this.type = type;
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

}
