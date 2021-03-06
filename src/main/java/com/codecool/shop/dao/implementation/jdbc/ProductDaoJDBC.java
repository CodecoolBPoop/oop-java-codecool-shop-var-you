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
        try (Connection connection = JDBCConnector.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, CAST(default_price AS FLOAT), description, product_category, supplier FROM product");) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                //Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier)
                //public Product(String name, float defaultPrice,String description)
                //Product(String name, float defaultPrice, String description, ProductCategory productCategory, Supplier supplier)
                Product prod = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("description"),
                        ProductCategoryDaoJDBC.getInstance().find(resultSet.getInt("product_category")),
                        SupplierDaoJDBC.getInstance().find(resultSet.getInt("supplier"))
                );
                data.add(prod);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return data;


    }



    @Override
    public List<Product> getBy(Supplier supplier) {
        int supplierId = supplier.getId();
        try (Connection connection = JDBCConnector.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, CAST(default_price AS FLOAT), description, product_category, supplier FROM product WHERE supplier = CAST (? AS INTEGER)");) {
            statement.setString(1, String.valueOf(supplierId));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier)
                //public Product(String name, float defaultPrice,String description)
                //Product(String name, float defaultPrice, String description, ProductCategory productCategory, Supplier supplier)
                Product prod = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("description"),
                        ProductCategoryDaoJDBC.getInstance().find(resultSet.getInt("product_category")),
                        SupplierDaoJDBC.getInstance().find(resultSet.getInt("supplier"))
                );
                data.add(prod);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        int productCategoryId = productCategory.getId();
        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id, name, CAST(default_price AS FLOAT), description," +
                                          " product_category, supplier FROM product WHERE product_category = CAST (? AS INTEGER)");) {
            statement.setString(1, String.valueOf(productCategoryId));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier)
                //public Product(String name, float defaultPrice,String description)
                //Product(String name, float defaultPrice, String description, ProductCategory productCategory, Supplier supplier)
                Product prod = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("description"),
                        ProductCategoryDaoJDBC.getInstance().find(resultSet.getInt("product_category")),
                        SupplierDaoJDBC.getInstance().find(resultSet.getInt("supplier"))
                );
                data.add(prod);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
