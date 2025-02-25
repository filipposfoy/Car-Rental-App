/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author filip
 */
public class booking {
    int rentID,licenceNumber,customer_id,driver_id;
    String start_date, end_date;
    
    
    public booking(int rentID, int licenceNumber, int customer_id, int driver_id, String start_date, String end_date) {
        this.rentID = rentID;
        this.licenceNumber = licenceNumber;
        this.customer_id = customer_id;
        this.driver_id = driver_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    // Setters
    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public void setLicenceNumber(int licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    // Getters
    public int getRentID() {
        return rentID;
    }

    public int getLicenceNumber() {
        return licenceNumber;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }
}
