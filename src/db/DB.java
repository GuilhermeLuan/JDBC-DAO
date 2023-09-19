package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DB {

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
