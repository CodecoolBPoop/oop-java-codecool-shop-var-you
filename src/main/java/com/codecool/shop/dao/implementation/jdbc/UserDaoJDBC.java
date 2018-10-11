package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.jdbc.JDBCConnector;
import com.codecool.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJDBC implements UserDao {

    @Override
    public void add(User user) {
        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO \"user\" (name, email, passwd) VALUES (?, ?, ?) ON CONFLICT DO NOTHING;");
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public User findUser(User user) {
        try (Connection connection = JDBCConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"user\" WHERE email=?");
        ) {
            statement.setString(1, user.getEmail());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                User newUser = new User(resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("passwd"));
                newUser.setId(resultSet.getString("id"));
                return newUser;
            } else {
                return null;
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
