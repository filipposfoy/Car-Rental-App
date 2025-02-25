/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.tables;

import subClasses.MotorVehicle;
import subClasses.Scooter;
import subClasses.Bike;
import com.google.gson.Gson;
import database.DB_connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mainClasses.Vehicle;

/**
 *
 * @author user
 */
public class EditVehiclesTable {

    public void addRentalVehicle(String json) throws ClassNotFoundException, Exception {
        Gson gson = new Gson();
        Vehicle user = gson.fromJson(json, Vehicle.class);

        System.out.println(user);
        addToDBVehicles(user);
    }

    public String addRentalMotorVehicle(String json) throws ClassNotFoundException, Exception {
        Gson gson = new Gson();
        MotorVehicle user = gson.fromJson(json, MotorVehicle.class);

        System.out.println(user);
        return addToDBMotorVehicles(user);
    }

    public String addRentalBike(String json) throws ClassNotFoundException, Exception {
        Gson gson = new Gson();
        Bike user = gson.fromJson(json, Bike.class);

        System.out.println(user);
        return addToDBBike(user);
    }

    public String addRentalScooter(String json) throws ClassNotFoundException, Exception {
        Gson gson = new Gson();
        Scooter user = gson.fromJson(json, Scooter.class);

        System.out.println(user);
        return addToDBScooter(user);
    }

    public String[] getMotorVehicle(long id) throws Exception {
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String query = "SELECT * FROM motor_vehicles WHERE licenceNumber = " + id + ";";

            ResultSet set = stmt.executeQuery(query);
            if (set.next()) {
                long licenceNumber = set.getLong("licenceNumber");
                int passengerCapacity = set.getInt("passengerCapacity");
                String carType = set.getString("carType");
                String mileage = set.getString("mileage");

                String[] result = {String.valueOf(licenceNumber), String.valueOf(passengerCapacity), carType, mileage};
                stmt.close();
                con.close();
                return result;
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

    public void addMalfunctionOrAccident(String parameter, String text, int licenceNumber) throws ClassNotFoundException, Exception {
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "UPDATE `vehicles` SET `" + parameter + "` = \"" + text + "\" WHERE licenceNumber = \"" + licenceNumber + "\";";

            System.out.println(insertQuery);

            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }
    }

    public void addToDBVehicles(Vehicle user) throws ClassNotFoundException, Exception {
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO `vehicles`(`licenceNumber`, `color`, `model`, `rentingCost`, `type`, `brand`, `isRented`, `under_service`, `malfunction`, `accident`) VALUES("
                    + "'" + user.getLicenceNumber() + "',"
                    + "'" + user.getColor() + "',"
                    + "'" + user.getModel() + "',"
                    + "'" + user.getRentingCost() + "',"
                    + "'" + user.getType() + "',"
                    + "'" + user.getBrand() + "',"
                    + "'" + user.getIsRented() + "',"
                    + "'" + user.getUnder_service() + "',"
                    + "'" + "n/a" + "',"
                    + "'" + "n/a" + "')";

            System.out.println(insertQuery);

            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }
    }

    public boolean isLicenceNumberUnique(int licenceNumber) throws ClassNotFoundException, Exception {

        int str = 0;
        ResultSet rs = null;

        System.out.println(licenceNumber);
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();


            String insertQuery = "SELECT COUNT(*) FROM vehicles WHERE licenceNumber = " + licenceNumber + " LIMIT 25";

            rs = stmt.executeQuery(insertQuery);
            if (rs.next()) {
                System.out.println(rs.getInt(1) + " this is what it returns");
                return rs.getInt(1) == 0;
            }

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return false;
    }


    public String addToDBMotorVehicles(MotorVehicle user) throws ClassNotFoundException, Exception {
        int str = 0;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO `motor_vehicles`(`licenceNumber`, `mileage`, `passengerCapacity`, `carType`) VALUES ("
                    + "'" + user.getLicenceNumber() + "',"
                    + "'" + user.getMileage() + "',"
                    + "'" + user.getPassengerCapacity() + "',"
                    + "'" + user.getCarType() + "')";

            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return "motor vehicle was added successfully with licence the number: " + user.getLicenceNumber();
    }

    public void rentVehicleStatus(int id, int flag) throws Exception {
        int str = 0;
        ResultSet rs = null;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "UPDATE vehicles SET isRented = " + flag + " WHERE licenceNumber = " + id + ";";
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }
    }

    public void setUnderService(int id, int flag) throws Exception {
        int str = 0;
        ResultSet rs = null;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "UPDATE vehicles SET under_service = " + flag + " WHERE licenceNumber = " + id + ";";
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }
    }

    public ArrayList<Vehicle> getAvailableMotorVehicles(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_connection.getConnection();
        Statement stmt = con.createStatement();
        String query;

        ArrayList<Vehicle> motor_vehicles = new ArrayList<>();
        ResultSet rs = null;
        if(type.equals("all")){
            query = "SELECT * FROM vehicles ";
        } else if (type.equals("filtered")) {
            query = "SELECT * FROM vehicles WHERE malfunction = 'n/a' AND accident = 'n/a' AND under_service = 0 AND isRented = 0";
        } else {
            query = "SELECT * FROM vehicles WHERE under_service = 0 AND isRented = 0 AND type = '" + type + "';";
        }    
        try {
            rs = stmt.executeQuery(query);
            System.out.println(query);

            while (rs.next()) {
                String json = DB_connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Vehicle vh = gson.fromJson(json, Vehicle.class);
                motor_vehicles.add(vh);
            }
            con.close();
            return motor_vehicles;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        return null;
    }


    public String addToDBBike(Bike user) throws ClassNotFoundException, Exception {
        int str = 0;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO `bikes`(`bike_id`) VALUES (" + "'" + user.getLicenceNumber() + "')";


            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }
        return "bike was added successfully with licence the number: " + user.getLicenceNumber();
    }


    public String addToDBScooter(Scooter user) throws ClassNotFoundException, Exception {
        int str = 0;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO `scooters`(`scooter_id`) VALUES (" + "'" + user.getLicenceNumber() + "')";

            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return "scooter was added successfully with licence the number: " + user.getLicenceNumber();
    }

}
