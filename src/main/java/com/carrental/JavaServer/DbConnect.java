package com.carrental.JavaServer;

import java.sql.*;

public class DbConnect {

    static final String dbUrl = "jdbc:mysql://localhost:3306/rent?serverTimezone=UTC";
    static final String username = "root";
    static final String password = "";
    static Connection dbConnection;
    static Statement dbStatement;


    private static void checkInit() throws SQLException {
        if ( DbConnect.dbConnection == null || DbConnect.dbStatement == null ) {
            DbConnect.dbConnection = DriverManager.getConnection(DbConnect.dbUrl, DbConnect.username, DbConnect.password);

            DbConnect.dbStatement = dbConnection.createStatement();
        }
    }
    public static ResultSet sendQuery(String query) throws SQLException {

        DbConnect.checkInit();

        return DbConnect.dbStatement.executeQuery(query);
    }
    public static int executeUpdate(String query) throws SQLException {

        DbConnect.checkInit();

        return DbConnect.dbStatement.executeUpdate(query);
    }
}
