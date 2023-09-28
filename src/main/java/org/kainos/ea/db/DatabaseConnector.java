package org.kainos.ea.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection conn;

    public Connection getConnection() throws SQLException {
        String user, password, host, name;

        if(conn != null && !conn.isClosed()) { return conn; }

        try {
            user = System.getenv("USER");
            password = System.getenv("PASSWORD");
            host = System.getenv("HOST");
            name = System.getenv("NAME");
            System.out.println("Host:" + host);
            System.out.println("Name:" + name);

            if(user == null || password == null || host == null || name == null){
                throw new IllegalArgumentException("Environment variables not set");
            }

            conn = DriverManager.getConnection("jdbc:mysql://"+ host +"/"+ name +"?useSSL=false", user, password);
            return conn;
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

}
