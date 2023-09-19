package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection connection = null;
    public static Connection getConnection(){
        if(connection == null){
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e){
                throw new DbExecption(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbExecption(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbExecption(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DbExecption(e.getMessage());
            }
        }
    }

    public static Properties loadProperties(){
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("db.properties")){
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e){
            throw new DbExecption(e.getMessage());
        }
    }
}
