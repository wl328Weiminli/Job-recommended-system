package com.laioffer.job.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.job.db.MySQLConnection;
import com.laioffer.job.entity.RegisterRequestBody;
import com.laioffer.job.entity.ResultResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        RegisterRequestBody body = mapper.readValue(request.getReader(), RegisterRequestBody.class);
        MySQLConnection connection = new MySQLConnection();
        ResultResponse resultResponse;
        if (connection.addUser(body.userId, body.password, body.firstName, body.lastName)) {
            resultResponse = new ResultResponse("OK");
        } else {
            resultResponse = new ResultResponse("User Already Exists");
        }
        connection.close();
        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), resultResponse);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
