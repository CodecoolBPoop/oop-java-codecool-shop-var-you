package com.codecool.shop.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutHandler {

    @WebServlet(urlPatterns = {"/login"})
    public class LoginHandler extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
            req.getSession().invalidate();
            response.sendRedirect("/");

        }
    }
}


