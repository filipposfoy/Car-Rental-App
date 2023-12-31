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
//                System.out.println(result);
                stmt.close();

                return result;
            }

            stmt.close();

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

        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return id;
    }

    public void addToDBVehicles(Vehicle user) throws ClassNotFoundException, Exception {
        int str = 0;
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO `vehicles`(`licenceNumber`, `color`, `model`, `rentingCost`, `type`, `brand`, `isRented`, `under_service`) VALUES("
                    + "'" + user.getLicenceNumber() + "',"
                    + "'" + user.getColor() + "',"
                    + "'" + user.getModel() + "',"
                    + "'" + user.getRentingCost() + "',"
                    + "'" + user.getType() + "',"
                    + "'" + user.getBrand() + "',"
                    + "'" + user.getIsRented() + "',"
                    + "'" + user.getUnder_service() + "')";

            System.out.println(insertQuery);

            stmt.executeUpdate(insertQuery);
            System.out.println("# The vehicle was successfully added in the database.");

            stmt.close();

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

//            System.out.println(insertQuery);

            rs = stmt.executeQuery(insertQuery);

            if (rs.next()) {
                return rs.getInt(1) == 0;
            }

            stmt.close();

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

        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return "motor vehicle was added successfully with licence the number: " + user.getLicenceNumber();
    }

    public ArrayList<? extends Vehicle> getAvailableMotorVehicles(String table) throws SQLException, ClassNotFoundException {
        Connection con = DB_connection.getConnection();
        Statement stmt = con.createStatement();

        ArrayList<Vehicle> motor_vehicles = new ArrayList<>();
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery("SELECT * FROM " + table + " WHERE isRented = 0;");

            while (rs.next()) {
                if (table.equals("motor_vehicles")) {
                    String json = DB_connection.getResultsToJSON(rs);
                    Gson gson = new Gson();
                    MotorVehicle vh = gson.fromJson(json, MotorVehicle.class);
                    motor_vehicles.add(vh);
                } else if (table.equals("bikes")) {
                    String json = DB_connection.getResultsToJSON(rs);
                    Gson gson = new Gson();
                    Bike vh = gson.fromJson(json, Bike.class);
                    motor_vehicles.add(vh);
                } else {
                    String json = DB_connection.getResultsToJSON(rs);
                    Gson gson = new Gson();
                    Scooter vh = gson.fromJson(json, Scooter.class);
                    motor_vehicles.add(vh);
                }
            }

            return motor_vehicles;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public ArrayList<Vehicle> getAvailableMotorVehicles2(String type) throws SQLException, ClassNotFoundException {
        Connection con = DB_connection.getConnection();
        Statement stmt = con.createStatement();

        ArrayList<Vehicle> motor_vehicles = new ArrayList<>();
        ResultSet rs = null;
        String query = "SELECT * FROM vehicles WHERE isRented = 0 AND type = '" + type + "';";
        try {
            rs = stmt.executeQuery(query);
            System.out.println(query);

            while (rs.next()) { // need to get id from scooters and bike and remove extra attributes from cars
                String json = DB_connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Vehicle vh = gson.fromJson(json, Vehicle.class);
                motor_vehicles.add(vh);
            }

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

        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return "scooter was added successfully with licence the number: " + user.getLicenceNumber();
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
                + "    licenceNumber BIGINT(20) not null unique,"
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
