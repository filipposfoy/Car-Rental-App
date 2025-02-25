/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import database.DB_connection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "printQuery", urlPatterns = {"/printQuery"})
public class printQuery extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String res = "";

        String user = request.getParameter("user");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(user);
        String text = jsonNode.get("text").asText();
        JsonObject jo = new JsonObject();

        try {
            jo.addProperty("str", returnAdditionalCost(text));
        } catch (Exception e) {
            e.printStackTrace();
        }

        jo.addProperty("color", "green");

        response.setContentType("application/json");
        response.getWriter().write(jo.toString());
    }

    public String returnAdditionalCost(String text) throws ClassNotFoundException, Exception {
        ResultSet response = null;
        String returnString = "";

        try {
            Connection con = DB_connection.getConnection();
            Statement stmt = con.createStatement();

            String query = text;

            ResultSet set = stmt.executeQuery(query);

            if (set.next()) {
                response = set;
                for (int i = 1; i <= response.getMetaData().getColumnCount(); i++) {
                    returnString += response.getMetaData().getColumnName(i) + "\t";
                    System.out.print(response.getMetaData().getColumnName(i) + "\t");
                }
                returnString += "\n";
                for (int i = 1; i <= response.getMetaData().getColumnCount(); i++) {
                    returnString += set.getString(i) + "\t";
                    System.out.print(set.getString(i) + "\t");
                }
                returnString += "\n";
                System.out.println();

                while (set.next()) {
                    for (int i = 1; i <= response.getMetaData().getColumnCount(); i++) {
                        returnString += set.getString(i) + "\t";
                        System.out.print(set.getString(i) + "\t");
                    }
                    returnString += "\n";
                    System.out.println();
                }
            }

            stmt.close();
            con.close();

            return returnString;
        } catch (SQLException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
        }

        return "";
    }
}
