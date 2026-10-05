package utils;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config properties file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        // System property overrides config file
        String sysProp = System.getProperty(key);
        if (sysProp != null && !sysProp.trim().isEmpty()) {
            return sysProp;
        }
        return properties.getProperty(key);
    }
}
