package com.codecool.shop.jdbc;

import com.codecool.shop.config.Props;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;

public class JDBCConnector {

    private static String ConnenctStringBuilder() {
        StringBuilder string = new StringBuilder();
        string.append("jdbc:postgresql://localhost:5432/");
        string.append(Props.getInstance().getDatabase());
        return string.toString();
    }


  private static final String DATABASE = ConnenctStringBuilder();
  private static final String DB_USER =  "tilla"; //Props.getInstance().getUsername();
  private static final String DB_PASSWORD = "xxx"; //Props.getInstance().getPassword();
  private static JDBCConnector instance = null;

  public Connection getConnection() {
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(
              DATABASE,
              DB_USER,
              DB_PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public static JDBCConnector getInstance() {
    if (instance == null) {
      instance = new JDBCConnector();
    }
    return instance;
  }


  public ResultSet executeQuery(String query) throws SQLException {
    try {
      Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement(query);
      return statement.executeQuery();
    } catch (SQLTimeoutException timeout) {
      System.err.println(timeout.getMessage());
    }
    return null;
  }

  public void executeUpdate(String query) throws SQLException {
    try {
      Connection connection= getConnection();
      PreparedStatement statement = connection.prepareStatement(query);
      statement.executeQuery();
    } catch (SQLException timeout) {
      System.err.println();}

  }



}
