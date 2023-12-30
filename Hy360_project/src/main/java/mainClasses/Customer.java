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
    String name, surname, birthdate, address;
    String creditCardNumber;

    public Customer(int customer_id, String name, String surname, String birthdate, String creditCardNumber, String address) {
        this.customer_id = customer_id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.creditCardNumber = creditCardNumber;
        this.address = address;
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

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
