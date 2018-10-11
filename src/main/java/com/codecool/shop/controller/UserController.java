package com.codecool.shop.controller;


import com.codecool.shop.dao.DaoSwitcher;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.jdbc.UserDaoJDBC;
import com.codecool.shop.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class UserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDao dummyname = new UserDaoJDBC();


        if (req.getParameter("register") != null) {
            String newEmail = req.getParameter("email");
            String newPass = req.getParameter("password");
            String newName = req.getParameter("name_input");
            User newUser = new User(newName, newEmail, newPass);
            dummyname.add(newUser);
            System.out.println("didit");
            resp.sendRedirect("/");
        }




    }





}
