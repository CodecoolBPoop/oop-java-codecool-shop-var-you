package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart-request"})
public class CartRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        /* DEmo cart setup */
        List<Product> cartContent = new ArrayList<>();
        ProductCategory demoCategory = new ProductCategory("demoCategory", "hardvare", "just for demo");
        Supplier demoSupplier = new Supplier("demosuplier", "Just for demo");
        Product demo1 = new Product("demo1", 300, "dollar", "Just for demo", demoCategory, demoSupplier);
        Product demo2 = new Product("demo2", 300, "dollar", "Just for demo", demoCategory, demoSupplier);
        Product demo3 = new Product("demo3", 300, "dollar", "Just for demo", demoCategory, demoSupplier);
        Product demo4 = new Product("demo4", 300, "dollar", "Just for demo", demoCategory, demoSupplier);
        cartContent.add(demo1);
        cartContent.add(demo2);
        cartContent.add(demo3);
        cartContent.add(demo4);

        context.setVariable("cartContent", cartContent);

    }


}
