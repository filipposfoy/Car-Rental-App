/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import database.tables.EditCustomersTable;

/**
 *
 * @author user
 */
@WebServlet(name = "addUser", urlPatterns = {"/addUser"})
public class addUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("user");
        EditCustomersTable table = new EditCustomersTable();
        JsonObject jo = new JsonObject();
        try {
            table.addCustomer(user);
        } catch (Exception e) {

        }

        jo.addProperty("str", user);

        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
