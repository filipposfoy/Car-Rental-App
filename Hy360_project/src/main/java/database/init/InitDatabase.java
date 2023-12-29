/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.init;

import java.sql.Connection;
import static database.DB_connection.getInitialConnection;
import java.sql.SQLException;
import java.sql.Statement;
import database.tables.EditCustomersTable;
import database.tables.EditVehiclesTable;


/**
 *
 * @author user
 */
public class InitDatabase {

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        InitDatabase database = new InitDatabase();
        database.initDatabase();
//        database.initTables();
//        database.addExamplesToTables();

    }

    public void initDatabase() throws SQLException, ClassNotFoundException {
        Connection conn = getInitialConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("Creating database Hy360");
        stmt.close();
        conn.close();
    }

    public void initTables() throws SQLException, ClassNotFoundException {
        EditCustomersTable table = new EditCustomersTable();
        table.createCustomersTable();
        EditVehiclesTable table2 = new EditVehiclesTable();
        table2.createMotorVehiclesTable();
        table2.createBikeTable();
        // Table t = new Table();
        // t.createTables();
    }

    public void addExamplesToTables() throws SQLException, ClassNotFoundException {

    }

}
