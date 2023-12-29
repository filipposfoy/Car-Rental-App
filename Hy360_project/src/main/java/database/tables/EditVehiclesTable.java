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

/**
 *
 * @author user
 */
public class EditVehiclesTable {

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

//        addToDB(user);
        return addToDBBike(user);
    }

    public String addRentalScooter(String json) throws ClassNotFoundException, Exception {
        Gson gson = new Gson();
        Scooter user = gson.fromJson(json, Scooter.class);

        System.out.println(user);

//        return addToDB(user);
        return addToDBScooter(user);
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

        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return id;
    }


    public String addToDBMotorVehicles(MotorVehicle user) throws ClassNotFoundException, Exception {
        int str = 0;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO `motor_vehicles`(`vehicle_id`, `color`, `model`, `rentingCost`, `type`, `brand`, `isRented`, `mileage`, `licenseNumber`, `passengerCapacity`, `carType`) VALUES("
                    + "'" + user.getVehicle_id() + "',"
                    + "'" + user.getColor() + "',"
                    + "'" + user.getModel() + "',"
                    + "'" + user.getRentingCost() + "',"
                    + "'" + user.getType() + "',"
                    + "'" + user.getBrand() + "',"
                    + "'" + user.getIsRented() + "',"
                    + "'" + user.getMileage() + "',"
                    + "'" + user.getLlcenseNumber() + "',"
                    + "'" + user.getPassengerCapacity() + "',"
                    + "'" + user.getCarType() + "')";

            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return "Your unique motor vehicle key is " + getLastId("motor_vehicles", "vehicle_id");
    }

    public String addToDBBike(Bike user) throws ClassNotFoundException, Exception {
        int str = 0;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO `bikes`(`bike_id`, `color`, `model`, `rentingCost`, `type`, `brand`, `isRented`) VALUES("
                    + "'" + user.getBike_id() + "',"
                    + "'" + user.getColor() + "',"
                    + "'" + user.getModel() + "',"
                    + "'" + user.getRentingCost() + "',"
                    + "'" + user.getType() + "',"
                    + "'" + user.getBrand() + "',"
                    + "'" + user.getIsRented() + "')";

            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }
        return "Your unique bike key is " + getLastId("bikes", "bike_id");
    }


    public String addToDBScooter(Scooter user) throws ClassNotFoundException, Exception {
        int str = 0;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO `scooters`(`scooter_id`, `color`, `model`, `rentingCost`, `type`, `brand`, `isRented`) VALUES("
                    + "'" + user.getScooter_id() + "',"
                    + "'" + user.getColor() + "',"
                    + "'" + user.getModel() + "',"
                    + "'" + user.getRentingCost() + "',"
                    + "'" + user.getType() + "',"
                    + "'" + user.getBrand() + "',"
                    + "'" + user.getIsRented() + "')";

            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return "Your unique scooter id key is " + getLastId("scooters", "scooter_id");
    }

    public void createMotorVehiclesTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE motor_vehicles "
                + "(vehicle_id INTEGER not NULL AUTO_INCREMENT, "
                + "    color VARCHAR(30) not null unique,"
                + "    model VARCHAR(30) not null unique,"
                + "    rentingCost BIGINT(20) not null unique,"
                + "    type VARCHAR(30) not null unique,"
                + "    brand VARCHAR(30) not null unique,"
                + "    isRented TINYINT(1) not null unique,"
                + "    mileage VARCHAR(30) not null unique,	"
                + "    licenseNumber BIGINT(20) not null unique,"
                + "    passengerCapacity INTEGER not null unique,	"
                + "    carType VARCHAR(30) not null,"
                + " PRIMARY KEY (vehicle_id))";
        stmt.execute(query);
        stmt.close();
    }

    public void createBikeTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE bikes "
                + "(bike_id INTEGER not NULL AUTO_INCREMENT, "
                + "    color VARCHAR(30) not null unique,"
                + "    model VARCHAR(30) not null unique,"
                + "    type VARCHAR(30) not null unique,"
                + "    brand VARCHAR(30) not null unique,"
                + "    rentingCost BIGINT(20) not null unique,"
                + "    isRented TINYINT(1) not null unique,"
                + " PRIMARY KEY (bike_id))";
        stmt.execute(query);
        stmt.close();
    }

    public void createScooterTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE scooters "
                + "(scooter_id INTEGER not NULL AUTO_INCREMENT, "
                + "    color VARCHAR(30) not null unique,"
                + "    model VARCHAR(30) not null unique,"
                + "    type VARCHAR(30) not null unique,"
                + "    brand VARCHAR(30) not null unique,"
                + "    rentingCost BIGINT(20) not null unique,"
                + "    isRented TINYINT(1) not null unique,"
                + " PRIMARY KEY (scooter_id))";
        stmt.execute(query);
        stmt.close();
    }

}
