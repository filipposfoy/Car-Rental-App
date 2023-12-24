/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author user
 */
public class Customer {

    int customer_id;
    String name, surname, birthdate;
    double creditCardNumber;

    public Customer(int customer_id, String name, String surname, String birthdate, double creditCardNumber) {
        this.customer_id = customer_id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.creditCardNumber = creditCardNumber;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public double getCreditCardNumber() {
        return creditCardNumber;
    }

    public void getCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void getName(String name) {
        this.name = name;
    }

    public void getSurname(String surname) {
        this.surname = surname;
    }

    public void getBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void getCreditCardNumber(double creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
