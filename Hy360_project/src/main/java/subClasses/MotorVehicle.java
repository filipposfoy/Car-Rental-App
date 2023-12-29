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

    long licenseNumber, passengerCapacity;
    String mileage, carType;

    public MotorVehicle(int vehicle_id, String color, String model, long rentingCost, String type, String brand, int isRented, String mileage, long licenseNumber, long passengerCapacity, String carType) {
        super(vehicle_id, color, model, rentingCost, type, brand, isRented);
        this.mileage = mileage;
        this.passengerCapacity = passengerCapacity;
        this.carType = carType;
        this.licenseNumber = licenseNumber;
    }


    public void setLicenseNumber(long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setPassengerCapacity(long passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public long getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarType() {
        return carType;
    }

    public long getLlcenseNumber() {
        return licenseNumber;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getMileage() {
        return mileage;
    }
}
