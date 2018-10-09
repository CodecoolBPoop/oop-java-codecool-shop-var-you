package com.codecool.shop.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Props {
    private static Props instance = null;

    private Props() {}

    public static Props getInstance() {
        if (instance == null) {
            instance = new Props();
        }
        return instance;
    }

    Map<String, String> variables = new HashMap<>();

    private void getProperties(){
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            variables.put("database", prop.getProperty("database"));
            variables.put("username", prop.getProperty("dbuser"));
            variables.put("password", prop.getProperty("dbpassword"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getDatabase(){
        return variables.get("database");
    }

    public String getUsername(){
        return variables.get("username");
    }

    public String getPassword(){
        return variables.get("password");
    }


}
