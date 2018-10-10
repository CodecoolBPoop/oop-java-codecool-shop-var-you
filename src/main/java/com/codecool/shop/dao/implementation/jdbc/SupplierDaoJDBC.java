package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.jdbc.JDBCConnector;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {


    @Override
    public void add(Supplier supplier) {
        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO supplier (name, description) VALUES (?, ?) ON CONFLICT DO NOTHING;")
        ) {
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            ResultSet resultSet = statement.executeQuery();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM supplier WHERE id=?")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new Supplier(resultSet.getString("name"),
                        resultSet.getString("description"));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM supplier WHERE id=?;")
        ) {
            statement.setInt(1, id);
            return;
        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public List<Supplier> getAll() {
        List<Supplier> resultList = new ArrayList<>();

        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM supplier");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Supplier actTodo = new Supplier(resultSet.getString("name"),
                        resultSet.getString("description")) ;
                resultList.add(actTodo);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
