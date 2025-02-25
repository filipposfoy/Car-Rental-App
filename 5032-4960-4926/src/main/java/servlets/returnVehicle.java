/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import database.tables.EditBookingsTable;
import database.tables.EditVehiclesTable;
import java.time.LocalDate;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "returnVehicle", urlPatterns = {"/returnVehicle"})
public class returnVehicle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("user");

        JsonObject jo = new JsonObject();
        EditBookingsTable table = new EditBookingsTable();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(user);
        String rent_id = jsonNode.get("vehicle_id").asText();
        String dateString = jsonNode.get("date").asText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        try {
            int vehicle_id = table.getVehicleId(rent_id);

            if (vehicle_id == 0) {
                jo.addProperty("str", "the booking does not exist");
                jo.addProperty("color", "red");
            } else {
                if (table.checkIfVehicleIsRented(String.valueOf(vehicle_id)) == false) {
                    jo.addProperty("str", "vehicle is not rented");
                    jo.addProperty("color", "red");
                } else {
                    EditVehiclesTable vtable = new EditVehiclesTable();
                    long var = table.returnAdditionalCost(rent_id, date);
                    if (var <= 0) {
                        jo.addProperty("str", "thank you for returning your vehicle on time");
                    } else {
                        jo.addProperty("str", "your additional cost is " + var * 48);
                    }

                    vtable.rentVehicleStatus(vehicle_id, 0);
                    jo.addProperty("color", "green");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }

}
