/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import database.tables.EditVehiclesTable;
/**
 *
 * @author user
 */
@WebServlet(name = "repairVehicle", urlPatterns = {"/repairVehicle"})
public class repairVehicle extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = "";
        String type = "";
        long time = 86400;

        String user = request.getParameter("user");
        JsonObject jo = new JsonObject();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(user);

        System.out.println(user);

        type = jsonNode.get("type").asText();
        id = jsonNode.get("vehicle_id").asText();

        time *= "maintenance".equals(type) ? 1 : 3;
        EditVehiclesTable table = new EditVehiclesTable();

        try {
            if (table.isLicenceNumberUnique(Integer.parseInt(id)) == true) {
                jo.addProperty("str", "vehicle doens't exist");

                response.setContentType("application/json");
                response.getWriter().write(jo.toString());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(time);

        final String uid = id;
        final long ftime = time;

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before set to 1");
                try {
                    table.setUnderService(Integer.parseInt(uid), 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
//                    Thread.sleep(10000);                 //1000 milliseconds is one second.
                    Thread.sleep(ftime * 1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("after set to 0");
                try {
                    table.setUnderService(Integer.parseInt(uid), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        jo.addProperty("str", user);

        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }
}
