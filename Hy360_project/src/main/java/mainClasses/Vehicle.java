package mainClasses;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user
 */
public class Vehicle {

    int vehicle_id;
    String color, functionality;
    int rentingCost, ensuranceCost;
    boolean isRented;

    public Vehicle(int vehicle_id, String color, String functionality, int rentingCost, int ensuranceCost, boolean IsRented) {
        this.color = color;
        this.functionality = functionality;
        this.rentingCost = rentingCost;
        this.ensuranceCost = ensuranceCost;
        this.isRented = isRented;

    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public String getColor() {
        return color;
    }

    public String getFunctionality() {
        return functionality;
    }

    public int getRentingCost() {
        return rentingCost;
    }

    public int getEnsuranceCost() {
        return ensuranceCost;
    }

    public boolean getIsRented() {
        return isRented;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public void setRentingCost(int rentingCost) {
        this.rentingCost = rentingCost;
    }

    public void setEnsuranceCost(int ensuranceCost) {
        this.ensuranceCost = ensuranceCost;
    }

    public void setIsRented(boolean isRented) {
        this.isRented = isRented;
    }
}
