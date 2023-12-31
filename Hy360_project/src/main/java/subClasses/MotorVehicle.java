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

    long passengerCapacity;
    String mileage, carType;

    public MotorVehicle(int vehicle_id, String color, String model, long rentingCost, String type, String brand, int isRented, int under_service, String mileage, long licenceNumber, long passengerCapacity, String carType) {
        super(vehicle_id, color, model, rentingCost, type, brand, isRented, under_service, licenceNumber);
        this.mileage = mileage;
        this.passengerCapacity = passengerCapacity;
        this.carType = carType;
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

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getMileage() {
        return mileage;
    }

    @Override
    public String printString(int num) {
        return "         <b>Motor vehicle<b> " + num + "<br><br>"
                + "brand             = " + getBrand() + "<br>"
                + "model             = " + getModel() + "<br>"
                + "color             = " + getColor() + "<br>"
                + "rentingCost       = " + getRentingCost() + "<br>"
                + "type              = " + getType() + "<br>"
                + "mileage           = " + mileage + "<br>"
                + "licenseNumber     = " + getLicenceNumber() + "<br>"
                + "passengerCapacity = " + passengerCapacity + "<br>"
                + "carType           = " + carType + "<br>";
    }

}
