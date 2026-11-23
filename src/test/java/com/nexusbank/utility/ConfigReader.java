package com.nexusbank.utility;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPERTIES = new Properties();

    static {
        String env = System.getProperty("environment", "dev");
        String resource = "config/" + env + "-config.properties";
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(resource)) {
            if (input == null) throw new IllegalStateException("Config not found: " + resource);
            PROPERTIES.load(input);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConfigReader() {}
    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) throw new IllegalArgumentException("Missing config key: " + key);
        return value;
    }
}
