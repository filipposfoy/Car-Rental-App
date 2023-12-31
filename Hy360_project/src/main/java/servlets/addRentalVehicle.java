/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.tables.EditVehiclesTable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


/**
 *
 * @author user
 */
@WebServlet(name = "addRentalCar", urlPatterns = {"/addRentalCar"})
public class addRentalVehicle extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String res = "";
        String type = "";
        int ln;

        String user = request.getParameter("user");
        JsonObject jo = new JsonObject();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(user);

        type = jsonNode.get("type").asText();
        ln = jsonNode.get("licenceNumber").asInt();

        EditVehiclesTable table = new EditVehiclesTable();

        try {
            if (table.isLicenceNumberUnique(ln) == false) {
                jo.addProperty("str", "licence number is not unique");
                jo.addProperty("color", "red");

                response.setContentType("application/json");
                response.getWriter().write(jo.toString());
                return;
            }
            table.addRentalVehicle(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (type) {
            case "Car":
            try {
                res = table.addRentalMotorVehicle(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;

            case "Bike":
            try {
                res = table.addRentalBike(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;

            case "Scooter":
            try {
                res = table.addRentalScooter(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        }

//        jo.addProperty("str", user);
        jo.addProperty("str", res);
        jo.addProperty("color", "green");

        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }
}
