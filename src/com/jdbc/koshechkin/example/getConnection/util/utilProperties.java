package com.jdbc.koshechkin.example.getConnection.util;

import com.jdbc.koshechkin.example.getConnection.ConnectionToDateBase;
import java.io.IOException;
import java.util.Properties;

public final class utilProperties {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String getKey(String key) {
        return PROPERTIES.getProperty(key);
    }
    private static void loadProperties() {
        try (var input = ConnectionToDateBase.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
