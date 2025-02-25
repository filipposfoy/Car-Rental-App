/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import database.tables.EditBookingsTable;
import database.tables.EditVehiclesTable;
import mainClasses.Customer;
import mainClasses.Vehicle;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author filip
 */
@WebServlet(name = "book", urlPatterns = {"/book"})
public class book extends HttpServlet {


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
        String res = "";
        EditVehiclesTable vtable = new EditVehiclesTable();
        boolean flag = true;
        boolean isMotorVehicle = true;
        boolean exists = false;

        String user = request.getParameter("book");
        EditBookingsTable table = new EditBookingsTable();
        JsonObject jo = new JsonObject();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(user);
        String customerId = jsonNode.get("customer_id").asText();
        String driverId = jsonNode.get("driver_id").asText();
        String vehicle_id = jsonNode.get("licenceNumber").asText();

        LocalDate start_date = LocalDate.parse(jsonNode.get("start_date").asText());
        LocalDate end_date = LocalDate.parse(jsonNode.get("end_date").asText());

        System.out.println("start date: " + start_date);
        System.out.println("end date: " + end_date);

        int compare = start_date.compareTo(end_date);

        if (compare > 0) {
            flag = false;
        }

        jo.addProperty("dcolor", (compare > 0) ? "red" : "green");

        try {
            try {
                Vehicle v = table.checkForVehicle(vehicle_id);
                if (v == null) {
                    jo.addProperty("vehicle", "vehicle does not exist");
                    jo.addProperty("vcolor", "red");
                    flag = false;
                } else {
                    jo.addProperty("vehicle", "");
                    jo.addProperty("vcolor", "green");
                    exists = true;
                    isMotorVehicle = (v.getType().equals("Car")) ? true : false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Customer c = table.checkForUser(customerId);
            if (c == null) {
                jo.addProperty("customer", "customer does not exist");
                jo.addProperty("ccolor", "red");
                flag = false;
            } else {
                jo.addProperty("customer", "");
                jo.addProperty("ccolor", "green");

                if (c.getCreditCardNumber().isEmpty()) {
                    jo.addProperty("card", "the customer does not have a credit card");
                    jo.addProperty("cardcolor", "red");
                    flag = false;
                } else {
                    jo.addProperty("card", "");
                    jo.addProperty("cardcolor", "green");
                }
            }

            if (driverId == "") {
                driverId = customerId;
            }

            Customer d = null;

            try {
                d = table.checkForUser(driverId);
            } catch (Exception e) {

            }
            if (d == null) {
                jo.addProperty("licence", "the driver does not exist");
                jo.addProperty("lcolor", "red");
                flag = false;
            } else {

                System.out.println("birthdatre: " + d.getBirthdate());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dob = LocalDate.parse(d.getBirthdate(), formatter);

                String yearString = String.valueOf(dob.getYear());

                System.out.println("year " + yearString);
                if (d.getLicence().isEmpty() && exists && isMotorVehicle) {
                    jo.addProperty("licence", "the driver does not have a driving licence");
                    jo.addProperty("lcolor", "red");
                    flag = false;
                } else {
                    if (!isMotorVehicle && exists && Integer.parseInt(yearString) > 2008) {
                        jo.addProperty("licence", "you must be above 16 to rent this vehicle.");
                        jo.addProperty("lcolor", "red");
                    } else if (isMotorVehicle && exists && Integer.parseInt(yearString) > 2005) {
                        jo.addProperty("licence", "you must be above 18 to rent this vehicle.");
                        jo.addProperty("lcolor", "red");
                    } else {
                        jo.addProperty("licence", "");
                        jo.addProperty("lcolor", "green");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("flag = " + flag);
        try {
            if (table.checkIfVehicleIsRented(vehicle_id) == true) {
                jo.addProperty("str", "vehicle is already rented");
                jo.addProperty("color", "red");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (flag == true && table.checkIfVehicleIsRented(vehicle_id) == false) {
                res = table.addBooking(user);
                vtable.rentVehicleStatus(Integer.parseInt(vehicle_id), 1);
                jo.addProperty("str", res);
                jo.addProperty("color", "green");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      

        if (flag == false) {
            jo.addProperty("str", "something went wrong");
            jo.addProperty("color", "red");
        }


        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }
}
