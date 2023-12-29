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
    String color, model, type, brand;
    long rentingCost;
    int isRented;

    public Vehicle(int vehicle_id, String color, String model, long rentingCost, String type, String brand, int IsRented) {
        this.color = color;
        this.model = model;
        this.rentingCost = rentingCost;
        this.brand = brand;
        this.type = type;
        this.isRented = isRented;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public long getRentingCost() {
        return rentingCost;
    }

    public String getType() {
        return type;
    }

    public int getIsRented() {
        return isRented;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setRentingCost(int rentingCost) {
        this.rentingCost = rentingCost;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIsRented(int isRented) {
        this.isRented = isRented;
    }
}
