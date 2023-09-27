package org.kainos.ea.db;

import org.kainos.ea.client.DatabaseConnectionException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private static Connection conn;

    public Connection getConnection() throws SQLException, DatabaseConnectionException {
        String user, password, host, name;

        if(conn != null && !conn.isClosed()) { return conn; }

        try(FileInputStream propsStream = new FileInputStream("db.properties")){
            Properties props = new Properties();

            props.load(propsStream);

            user = props.getProperty("user");
            password = props.getProperty("password");
            host = props.getProperty("host");
            name = props.getProperty("name");

            if(user == null || password == null || host == null || name == null){
                throw new IllegalArgumentException("Properties file must exist with the correct data inside!");
            }

            conn = DriverManager.getConnection("jdbc:mysql://"+ host +"/"+ name +"?useSSL=false", user, password);
            return conn;
        }catch (Exception e){
            System.err.println(e.getMessage());
            throw new DatabaseConnectionException();
        }

    }

}
