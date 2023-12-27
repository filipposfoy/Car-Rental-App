/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.tables;

import mainClasses.Customer;
import java.sql.Statement;
import database.DB_connection;
import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.ResultSet;
import com.google.gson.Gson;



/**
 *
 * @author user
 */
public class EditCustomersTable {

    public void addCustomer(String json) throws ClassNotFoundException {
        Gson gson = new Gson();
        Customer user = gson.fromJson(json, Customer.class);
        System.out.println(user);
        addToDB(user);
    }

    public void addToDB(Customer user) throws ClassNotFoundException {
        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO `customers` (`customer_id`, `name`, `surname`, `birthdate`, `creditcard`) VALUES ("
                    + "'" + user.getCustomer_id() + "',"
                    + "'" + user.getName() + "',"
                    + "'" + user.getSurname() + "',"
                    + "'" + user.getBirthdate() + "',"
                    + "'" + user.getCreditCardNumber() + "')";

            stmt.executeUpdate(insertQuery);
            System.out.println("# The customer was successfully added in the database.");

            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }
    }

    public void createPetOwnersTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE customers "
                + "(customer_id INTEGER not NULL AUTO_INCREMENT, "
                + "    name VARCHAR(30) not null unique,"
                + "    surname VARCHAR(30) not null unique,	"
                + "    birthdate DATE not null,"
                + "    creditcard VARCHAR(30) not null,"
                + " PRIMARY KEY (customer_id))";
        stmt.execute(query);
        stmt.close();
    }
}
