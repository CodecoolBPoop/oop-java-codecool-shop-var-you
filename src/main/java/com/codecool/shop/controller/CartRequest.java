package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart-request"})
public class CartRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        /* Demo cart setup */
        List<Product> cartContent = new ArrayList<>();
        ProductCategory demoCategory = new ProductCategory("demoCategory", "hardvare", "just for demo");
        Supplier demoSupplier = new Supplier("demosuplier", "Just for demo");
        Product demo1 = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", demoCategory, demoSupplier);
        Product demo2 = new Product("Amazon Earth", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", demoCategory, demoSupplier);
        Product demo3 = new Product("Amazon Water", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", demoCategory, demoSupplier);
        Product demo4 = new Product("Amazon Air", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", demoCategory, demoSupplier);
        cartContent.add(demo1);
        cartContent.add(demo2);
        cartContent.add(demo3);
        cartContent.add(demo4);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        //create Json Object
        JSONArray arrayJson = new JSONArray();

        for(Product product : cartContent) {
            JSONObject obj = new JSONObject();
            obj.put("name", product.getName());
            obj.put("price", product.getPrice());
            arrayJson.add(obj);
        }

        // finally output the json string
        out.println(arrayJson.toJSONString());
        arrayJson.clear();


    }


}
