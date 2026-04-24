package com.n11.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Reads test configuration values from src/test/resources/config.properties
public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config.properties: " + e.getMessage(), e);
        }
    }

    // Returns the trimmed string value for the given key; throws if the key is missing
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value.trim();
    }
}
