package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.jdbc.JDBCConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoJDBC implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;
    private JDBCConnector db = JDBCConnector.getInstance();


    @Override
    public void add(Product product) {
      Supplier supplier;
      ProductCategory productCategory;
      supplier = product.getSupplier();
      productCategory = product.getProductCategory();

        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO product (name, default_price, description, product_category, supplier, currency) " +
                     "VALUES (?, CAST(? as DOUBLE PRECISION ), ?, ?, ?, ?) ON CONFLICT DO NOTHING;");
        ) {
            statement.setString(1, product.getName());
            statement.setString(2, Double.toString(product.getDefaultPrice()));
            statement.setString(3, product.getDescription());


            if (productCategory.getName() == "Tablet"){
                statement.setInt(4, 1);
            } else if (productCategory.getName() == "Medicine") {
                statement.setInt(4, 2);
            }else {statement.setInt(4, 3);}


            if (supplier.getName() == "Amazon") {
                statement.setInt(5, 1);
            } else if (supplier.getName() == "Lenovo") {
                statement.setInt(5, 2);
            } else if (supplier.getName() == "Chinese store") {
                statement.setInt(5, 3);
            } else if (supplier.getName() == "Pharmacy") {
                statement.setInt(5, 4);
            } else { statement.setInt(5, 5);
            }


            if (product.getDefaultCurrency().toString() == "HUF") {
                statement.setInt(6, 1);
            } else {
                statement.setInt(6, 2);

            }
            ResultSet resultSet = statement.executeQuery();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public  List<Product> getAll() {


      return null;

    }



    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
