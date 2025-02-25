/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import database.tables.EditVehiclesTable;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "issue", urlPatterns = {"/issue"})
public class issue extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String res = "";

        String user = request.getParameter("user");
        EditVehiclesTable table = new EditVehiclesTable();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(user);

        int licenceNumber = jsonNode.get("carId").asInt();
        String text = jsonNode.get("text").asText();
        String paramenter = (jsonNode.get("flag").asInt() == 0) ? "malfunction" : "accident";
        JsonObject jo = new JsonObject();

        try {
            table.addMalfunctionOrAccident(paramenter, text, licenceNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        jo.addProperty("str", (jsonNode.get("flag").asInt() == 0) ? "thank you for reporting a malfunction" : "thank you for reporting an accident");
        jo.addProperty("color", "green");

        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }
}
