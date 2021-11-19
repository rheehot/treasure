package com.board;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mvcBoard?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String username = "root";
    private static final String password = "1234";

    @Test
    public void testConnection() throws Exception {
        Class.forName(DRIVER);
        try {
            Connection connection = DriverManager.getConnection(URL, username, password);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
