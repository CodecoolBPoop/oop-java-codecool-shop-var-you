package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = DaoSwitcher.getInstance().getProductDao();
        ProductCategoryDao productCategoryDataStore = DaoSwitcher.getInstance().getProductCategoryDao();
        SupplierDao supplierDataStore = DaoSwitcher.getInstance().getSupplierDao();
        HashMap<String, String> websiteName = new HashMap<>();
        websiteName.put("name", "CodeCool Survival Shop");


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("recipient", "World");
        context.setVariable("allproducts", productCategoryDataStore.getAll());
        context.setVariable("allsuppliers", supplierDataStore.getAll());
        if (req.getParameter("category") != null) {
            context.setVariable("category", productCategoryDataStore.find(Integer.parseInt(req.getParameter("category"))));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(req.getParameter("category")))));
        } else if (req.getParameter("supplier") != null) {
            context.setVariable("category", supplierDataStore.find(Integer.parseInt(req.getParameter("supplier"))));
            context.setVariable("products", productDataStore.getBy(supplierDataStore.find(Integer.parseInt(req.getParameter("supplier")))));
        } else {
            context.setVariable("category", websiteName);
            context.setVariable("products", productDataStore.getAll());
        }
        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductDao productDataStore = DaoSwitcher.getInstance().getProductDao();
        ShoppingCartDao shoppingCart = DaoSwitcher.getInstance().getShopplingCartDao();

        if (req.getParameter("id") != null) {
            shoppingCart.add(productDataStore.find(Integer.parseInt(req.getParameter("id"))));
        } else if (req.getParameter("add") != null) {
            shoppingCart.add(productDataStore.find(Integer.parseInt(req.getParameter("add"))));
        } else if (req.getParameter("remove") != null) {
            shoppingCart.remove(Integer.parseInt(req.getParameter("remove")));
        }



        resp.sendRedirect(req.getHeader("referer"));
    }
}
