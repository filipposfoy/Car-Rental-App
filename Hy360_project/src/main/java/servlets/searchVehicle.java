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

        String res = "";

        JsonObject jo = new JsonObject();

        String user = request.getParameter("user");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(user);
        String str = jsonNode.get("type").asText();
        EditVehiclesTable table = new EditVehiclesTable();
        ArrayList<Vehicle> arr = new ArrayList();

        try {
            arr = table.getAvailableMotorVehicles2(str);
        } catch (Exception e) {

        }
        String output = "";

        for (int i = 0; i < arr.size(); i++) {
            Vehicle v = (Vehicle) arr.get(i);
            System.out.println("(" + v.getType() + ")");

            if ("Car".equals(v.getType())) {
                try {
                    String[] fields = table.getMotorVehicle(v.getLicenceNumber());
                    MotorVehicle vs = new MotorVehicle((int) v.getLicenceNumber(), v.getColor(), v.getModel(), v.getRentingCost(), v.getType(), v.getBrand(), v.getIsRented(), v.getUnder_service(), fields[3], v.getLicenceNumber(), Long.parseLong(fields[1]), fields[2]);
                    jo.addProperty("" + i, vs.printString(i + 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if ("Bike".equals(v.getType())) {
                Bike vs = new Bike(v.getVehicle_id(), v.getColor(), v.getModel(), v.getRentingCost(), v.getType(), v.getBrand(), v.getIsRented(), v.getUnder_service(), (int) v.getLicenceNumber(), v.getLicenceNumber());
                jo.addProperty("" + i, vs.printString(i + 1));
            } else if ("Scooter".equals(v.getType())) {
                Scooter vs = new Scooter(v.getVehicle_id(), v.getColor(), v.getModel(), v.getRentingCost(), v.getType(), v.getBrand(), v.getIsRented(), v.getUnder_service(), (int) v.getLicenceNumber(), v.getLicenceNumber());
                jo.addProperty("" + i, vs.printString(i + 1));
            }
        }

//            if (arr.get(i) instanceof MotorVehicle) {
//                MotorVehicle v = (MotorVehicle) arr.get(i);
//                if (v.getType() == "Car") {
//                    jo.addProperty("" + i, v.printString(i + 1));
//                    ;
//                }
//            }
//            if (arr.get(i) instanceof Bike) {
//                Bike v = (Bike) arr.get(i);
//                if (v.getType() == "Bike") {
//                    jo.addProperty("" + i, v.printString(i + 1));
//                }
//            }
//            if (arr.get(i) instanceof Scooter) {
//                Scooter v = (Scooter) arr.get(i);
//                if (v.getType() == "Scooter") {
//                    jo.addProperty("" + i, v.printString(i + 1));
//                }
//            }
        jo.addProperty("str", arr.size());

        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }

}
