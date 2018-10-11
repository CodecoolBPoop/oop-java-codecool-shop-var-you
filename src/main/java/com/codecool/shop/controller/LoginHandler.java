package com.codecool.shop.controller;


import com.codecool.shop.dao.DaoSwitcher;
import com.codecool.shop.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
public class LoginHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            User user = DaoSwitcher.getInstance().getUserDao().findUser(email);
            if (password.equals(user.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("/");
            } else {
                out.println("<script>alert('Invalid login data');</script>");
            }
        } catch (Exception error) {
            out.println("<script>alert('Error while login');</script>");
        }
    

    }

}
