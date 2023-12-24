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
public class Renter extends Customer {

    int rentee_id;

    public Renter(int customer_id, String name, String surname, String birthdate, double creditCardNumber, int rentee_id) {
        super(customer_id, name, surname, birthdate, creditCardNumber);
        this.rentee_id = rentee_id;
    }

    public void setRentee_id(int rentee_id) {
        this.rentee_id = rentee_id;
    }

    public double getRentee_id() {
        return rentee_id;
    }

}
