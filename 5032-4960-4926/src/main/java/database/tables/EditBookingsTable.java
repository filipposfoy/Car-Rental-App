/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.tables;

import mainClasses.booking;
import mainClasses.Customer;
import mainClasses.Vehicle;
import java.sql.Statement;
import database.DB_connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import com.google.gson.Gson;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author filip
 */
public class EditBookingsTable {

    public String addBooking(String json) throws ClassNotFoundException, Exception {
        Gson gson = new Gson();
        booking book = gson.fromJson(json, booking.class);
        System.out.println(json);

        return addToDB(book);
    }
        
    public Customer checkForUser(String id) throws ClassNotFoundException, Exception {
        ResultSet rs = null;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "SELECT * FROM `customers` WHERE customer_id = " + id + ";";
            rs = stmt.executeQuery(insertQuery);

            if (rs.next()) {
                String json = DB_connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                System.out.println(json);
                return gson.fromJson(json, Customer.class);
            }

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return null;
    }

    public boolean checkIfVehicleIsRented(String id) throws ClassNotFoundException, Exception {
        ResultSet rs = null;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "SELECT * FROM `vehicles` WHERE licenceNumber = " + id + ";";
            rs = stmt.executeQuery(insertQuery);

            if (rs.next()) {
                String json = DB_connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                System.out.println(json);
                Vehicle v = gson.fromJson(json, Vehicle.class);
                return v.getIsRented() == 1;
            }

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return false;
    }

    public Vehicle checkForVehicle(String id) throws ClassNotFoundException, Exception {
        ResultSet rs = null;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "SELECT * FROM `vehicles` WHERE licenceNumber = " + id + ";";
            rs = stmt.executeQuery(insertQuery);

            if (rs.next()) {
                String json = DB_connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                System.out.println(json);
                return gson.fromJson(json, Vehicle.class);
            }

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return null;
    }

    public static int getLastId(String table, String uid) throws Exception {
        int id = 0;

        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String query = "SELECT " + uid + " FROM " + table + " ORDER BY " + uid + " DESC LIMIT 1";

            ResultSet set = stmt.executeQuery(query);
            if (set.next()) {
                id = set.getInt(uid);
            }

            id = stmt.getGeneratedKeys().getInt(1);
            System.out.println("key is " + stmt.getGeneratedKeys().getInt(1));
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return id;
    }

    public int getVehicleId(String rentID) throws Exception {
        int id = 0;

        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String query = "SELECT * FROM bookings WHERE rentID = " + rentID + ";";

            ResultSet set = stmt.executeQuery(query);
            if (set.next()) {
                id = set.getInt(2);
            }

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return id;
    }

    public long returnAdditionalCost(String rentID, LocalDate date) throws ClassNotFoundException, Exception {
        String rentDate = "";

        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String query = "SELECT * FROM bookings WHERE rentID = " + rentID + ";";

            ResultSet set = stmt.executeQuery(query);
            if (set.next()) {
                rentDate = set.getString(4);
            }
            LocalDate rdate = LocalDate.parse(rentDate, DateTimeFormatter.ISO_DATE);

            long daysDifference = ChronoUnit.DAYS.between(rdate, date);

            System.out.println("The difference in days is: " + daysDifference);
            stmt.close();
            con.close();

            return daysDifference;
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return 0;
    }
    
    public String addToDB(booking kratisi) throws ClassNotFoundException, Exception {

        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO `bookings` (`licenceNumber`, `start_date`, `end_date`, `customer_id`, `driver_id`) VALUES ("
                    + "'" + kratisi.getLicenceNumber() + "',"
                    + "'" + kratisi.getStart_date() + "',"
                    + "'" + kratisi.getEnd_date() + "',"
                    + "'" + kratisi.getCustomer_id() + "',"
                    + "'" + kratisi.getDriver_id() + "')";
            
            
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
           
            System.out.println("# The booking was successfully added in the database.");

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return "your booking id is: " + getLastId("bookings", "rentID");
    }
}
