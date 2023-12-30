/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.tables.EditVehiclesTable;
import java.util.ArrayList;
import subClasses.Bike;
import subClasses.Scooter;
import subClasses.MotorVehicle;

/**
 *
 * @author user
 */
@WebServlet(name = "searchVehicle", urlPatterns = {"/searchVehicle"})
public class searchVehicle extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String res = "";

        JsonObject jo = new JsonObject();

        String user = request.getParameter("user");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(user);
        String str = jsonNode.get("type").asText();
        EditVehiclesTable table = new EditVehiclesTable();
        ArrayList<?> arr = new ArrayList();

        try {
            arr = table.getAvailableMotorVehicles(str);
        } catch (Exception e) {

        }
        String output = "";

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) instanceof MotorVehicle) {
                MotorVehicle v = (MotorVehicle) arr.get(i);
                jo.addProperty("" + i, v.printString(i + 1));
            } else if (arr.get(i) instanceof Bike) {
                Bike v = (Bike) arr.get(i);
                jo.addProperty("" + i, v.printString(i + 1));
            } else if (arr.get(i) instanceof Scooter) {
                Scooter v = (Scooter) arr.get(i);
                jo.addProperty("" + i, v.printString(i + 1));
            }
        }
        jo.addProperty("str", arr.size());

        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }

}
