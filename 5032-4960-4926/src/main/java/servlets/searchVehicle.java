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
import mainClasses.Vehicle;
import subClasses.Bike;
import subClasses.MotorVehicle;
import subClasses.Scooter;

/**
 *
 * @author user
 */
@WebServlet(name = "searchVehicle", urlPatterns = {"/searchVehicle"})
public class searchVehicle extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JsonObject jo = new JsonObject();
        EditVehiclesTable table = new EditVehiclesTable();
        ArrayList<Vehicle> arr = new ArrayList();
        ObjectMapper objectMapper = new ObjectMapper();

        String user = request.getParameter("user");

        JsonNode jsonNode = objectMapper.readTree(user);
        String str = jsonNode.get("type").asText();


        System.out.println("goes here ");

        try {
            arr = table.getAvailableMotorVehicles(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("goes here also " + arr);

        for (int i = 0; i < arr.size(); i++) {
            Vehicle v = (Vehicle) arr.get(i);
            System.out.println("(" + v.getType() + ")");

            if ("Car".equals(v.getType())) {
                try {
                    String[] fields = table.getMotorVehicle(v.getLicenceNumber());
                    MotorVehicle vs = new MotorVehicle((int) v.getLicenceNumber(), v.getColor(), v.getModel(), v.getRentingCost(), v.getType(), v.getBrand(), v.getIsRented(), v.getUnder_service(), fields[3], v.getLicenceNumber(), Long.parseLong(fields[1]), fields[2]);
                    String output = vs.printString(i + 1);
                    if (!"n/a".equals(v.getMalfunction())) {
                        output += "malfunction = " + v.getMalfunction() + "<br>";
                    }
                    if (!"n/a".equals(v.getAccident())) {
                        output += "accident = " + v.getAccident() + "<br>";
                    }
                    jo.addProperty("" + i, output);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if ("Bike".equals(v.getType())) {
                Bike vs = new Bike(v.getVehicle_id(), v.getColor(), v.getModel(), v.getRentingCost(), v.getType(), v.getBrand(), v.getIsRented(), v.getUnder_service(), (int) v.getLicenceNumber(), v.getLicenceNumber());
                String output = vs.printString(i + 1);
                if (!"n/a".equals(v.getMalfunction())) {
                    output += "malfunction = " + v.getMalfunction() + "<br>";
                }
                if (!"n/a".equals(v.getAccident())) {
                    output += "accident = " + v.getAccident() + "<br>";
                }
                jo.addProperty("" + i, output);
            } else if ("Scooter".equals(v.getType())) {
                Scooter vs = new Scooter(v.getVehicle_id(), v.getColor(), v.getModel(), v.getRentingCost(), v.getType(), v.getBrand(), v.getIsRented(), v.getUnder_service(), (int) v.getLicenceNumber(), v.getLicenceNumber());
                String output = vs.printString(i + 1);
                if (!"n/a".equals(v.getMalfunction())) {
                    output += "malfunction = " + v.getMalfunction() + "<br>";
                }
                if (!"n/a".equals(v.getAccident())) {
                    output += "accident = " + v.getAccident() + "<br>";
                }
                jo.addProperty("" + i, output);
            }
        }

        jo.addProperty("str", arr.size());

        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }

}
