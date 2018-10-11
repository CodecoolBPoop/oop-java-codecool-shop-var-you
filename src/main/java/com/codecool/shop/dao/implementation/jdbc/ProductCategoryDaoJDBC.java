package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.jdbc.JDBCConnector;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {
            try (Connection connection = JDBCConnector.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO product_category (name, department, description) VALUES (?, ?, ?) ON CONFLICT DO NOTHING;");
            ) {
                statement.setString(1, category.getName());
                statement.setString(2, category.getDepartment());
                statement.setString(3, category.getDescription());
                ResultSet resultSet = statement.executeQuery();
            }  catch (SQLException e) {
                e.printStackTrace();
            }
        }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> resultList = new ArrayList<>();

        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM product_category");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ProductCategory actTodo = new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")) ;
                resultList.add(actTodo);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }



    @Override
    public ProductCategory find(int id) {
        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM product_category WHERE id=?");
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                ProductCategory prodCat = new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")) ;
                return prodCat;
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM product_category WHERE id=?;");
        ) {
            statement.setInt(1, id);
            return;
        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
