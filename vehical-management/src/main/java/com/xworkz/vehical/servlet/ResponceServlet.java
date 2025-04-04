package com.xworkz.vehical.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/response")
public class ResponceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        String vehicleName = req.getParameter("vehicle_Name");
        String ownerName = req.getParameter("ownerName");
        String companyName = req.getParameter("companyName");
        System.out.println("Vehical Name: "+vehicleName);


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehical","root","q1w2e3r4t5y6");
           System.out.println("Connection: "+connection);
           PreparedStatement preparedStatement = connection.prepareStatement("insert into vehical_info(id, vehical_Name, companyName, ownerName) values(?,?,?,?)");
           preparedStatement.setInt(1,id);
           preparedStatement.setString(2,vehicleName);
           preparedStatement.setString(3,companyName);
           preparedStatement.setString(4,ownerName);
           preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");
        pw.println("<h1>Data saved successfully</h1>");
        pw.println("test message");

    }
}
