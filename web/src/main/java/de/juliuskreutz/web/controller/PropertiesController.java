package de.juliuskreutz.web.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesController {

    private static final String PROPERTIES_PATH = "properties.properties";
    private static final Properties PROPERTIES = init();

    public static int getMaxFileSize() {
        String val = (String) PROPERTIES.get("max-file-size");

        if (val == null) return 100;
        else return Integer.parseInt(val);
    }

    public static void storeMaxFileSize(int maxFileSize) {
        PROPERTIES.put("max-file-size", String.valueOf(maxFileSize));
        try {
            PROPERTIES.store(new FileOutputStream(PROPERTIES_PATH), null);
        } catch (IOException ignored) {
            System.out.println("Couldn't store properties");
        }
    }

    public static String getFileSavePath() {
        String val = (String) PROPERTIES.get("file-save-path");

        if (val == null) return "/home/ubuntu/uploads";
        else return val;
    }

    public static void storeFileSavePath(String fileSavePath) {
        PROPERTIES.put("file-save-path", fileSavePath);
        try {
            PROPERTIES.store(new FileOutputStream(PROPERTIES_PATH), null);
        } catch (IOException ignored) {
            System.out.println("Couldn't store properties");
        }
    }

    private static Properties init() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_PATH));
        } catch (IOException ignored) {
            System.out.println("No properties yet");
        }
        return properties;
    }
}
