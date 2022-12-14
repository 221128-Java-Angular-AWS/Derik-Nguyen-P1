package com.revature.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    //Singleton
    private static Connection connection;


    private ConnectionManager() { }





    public static Connection getConnection() {
        if(connection == null) {
            connection = connect();
        }
        return connection;
    }


    public static void test() {
        connect();
    }
    private static Connection connect() {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader.getResourceAsStream("jdbc.properties");

        try {
            props.load(inputStream);

            StringBuilder builder = new StringBuilder();
            builder.append("jdbc:postgresql://");
            builder.append(props.getProperty("host"));
            builder.append(":");
            builder.append(props.getProperty("port"));
            builder.append("/");
            builder.append(props.getProperty("dbname"));
            builder.append("?user=");
            builder.append(props.getProperty("username"));
            builder.append("&password=");
            builder.append(props.getProperty("password"));

            //System.out.println(builder.toString());

            Class.forName(props.getProperty("driver"));

            connection = DriverManager.getConnection(builder.toString());




        } catch(IOException | SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
