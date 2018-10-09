package com.codecool.shop.jdbc;

import java.sql.*;

public class JDBCConnector {
  private static final String DATABASE = "jdbc:postgresql://localhost:5432/todolist";
  private static final String DB_USER = "tilla";
  private static final String DB_PASSWORD = "xxx";
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
