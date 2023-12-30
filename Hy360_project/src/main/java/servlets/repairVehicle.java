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
        String vtype = "";

        String user = request.getParameter("user");
        JsonObject jo = new JsonObject();

//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(user);

        System.out.println(user);

//        type = jsonNode.get("type").asText();
//        id = jsonNode.get("vehicle_id").asText();
//        vtype = jsonNode.get("vehicle_type").asText();

//        if (type == "repair") {

//        } else {
//        }
//        System.out.println("repair type " + vtype);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("before");
////                isBeingAffectedByTheAbilityThatTakes5Seconds = true;
//                try {
//                    Thread.sleep(4000);                 //1000 milliseconds is one second.
//                } catch (InterruptedException ex) {
//                    Thread.currentThread().interrupt();
//                }
//                System.out.println("after");
////                isBeingAffectedByTheAbilityThatTakes5Seconds = false;
//            }
//        }).start();

//        jo.addProperty("str", user);

        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }
}
