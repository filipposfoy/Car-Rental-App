/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author user
 */
public class RentingOrder {

    int customer_id, rentOrder_id, vehicle_id;
    double transactionSum, duration;
    String dateOfOrder, name, surname;

    public RentingOrder(int customer_id, int rentOrder_id, int vehicle_id, double transactionSum, double duration, String dateOfOrder, String name, String surname) {
        this.customer_id = customer_id;
        this.dateOfOrder = dateOfOrder;
        this.duration = duration;
        this.name = name;
        this.surname = surname;
        this.rentOrder_id = rentOrder_id;
        this.vehicle_id = vehicle_id;
        this.transactionSum = transactionSum;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setDuration(String duration) {

        this.surname = surname;
    }

    public void setRentOrder_id(int rentOrder_id) {

        this.rentOrder_id = rentOrder_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public void setTransactionSum(int transactionSum) {

        this.transactionSum = transactionSum;
    }


}
