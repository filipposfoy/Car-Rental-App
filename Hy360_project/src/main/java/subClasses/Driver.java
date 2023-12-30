/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subClasses;

import mainClasses.Customer;

/**
 *
 * @author user
 */
public class Driver extends Customer {

    double licenseNumber;

    public Driver(int customer_id, String name, String surname, String birthdate, String creditCardNumber, double licenseNumber, String address) {
        super(customer_id, name, surname, birthdate, creditCardNumber, address);
        this.licenseNumber = licenseNumber;
    }

    public void setLicenseNumber(double licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public double getLicenseNumber() {
        return licenseNumber;
    }
}
