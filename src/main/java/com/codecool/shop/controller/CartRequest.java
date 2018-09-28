package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/cart-request"})
public class CartRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

        /*cart setup */
        List<Product> cartContent = ShoppingCartDaoMem.getInstance().getAll();


        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        //create Json Array
        JSONArray arrayJson = new JSONArray();

        for(Product product : cartContent) {
            JSONObject obj = new JSONObject();
            obj.put("name", product.getName());
            obj.put("price", product.getPrice());
            obj.put("id", product.getId());
            arrayJson.add(obj);
        }

        // finally output the json string
        out.println(arrayJson.toJSONString());
        arrayJson.clear();


    }


}
