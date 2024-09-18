package com.advanced.api.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();
    private static boolean useWireMock;
    private static JsonObject mockConfig;

    static {
        String env = System.getProperty("env", "test");
        String propertiesFile = "config-" + env + ".properties";
        String jsonFile = "mock-config.json";

        try (InputStream propertiesInput = Config.class.getClassLoader().getResourceAsStream(propertiesFile);
             InputStream jsonInput = Config.class.getClassLoader().getResourceAsStream(jsonFile)) {

            if (propertiesInput == null) {
                throw new RuntimeException("Configuration file not found: " + propertiesFile);
            }
            properties.load(propertiesInput);
            useWireMock = Boolean.parseBoolean(properties.getProperty("use.wiremock", "true"));


            if (jsonInput == null) {
                throw new RuntimeException("Configuration file not found: " + jsonFile);
            }
            mockConfig = JsonParser.parseReader(new InputStreamReader(jsonInput)).getAsJsonObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        if (useWireMock) {
            return "http://localhost:8990";
        }
        return properties.getProperty("base.url");
    }

    public static boolean isUseWireMock() {
        return useWireMock;
    }
    public static String getUserApiUrl() {
        return mockConfig.getAsJsonObject("mocks").get("userApi").getAsString();
    }

    public static String getProductApiUrl() {
        return mockConfig.getAsJsonObject("mocks").get("resourceApi").getAsString();
    }

    public static String getApiKey() {
        return properties.getProperty("api.key");
    }
}