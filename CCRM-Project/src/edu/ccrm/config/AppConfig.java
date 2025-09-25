package edu.ccrm.config;

public class AppConfig {
    private static AppConfig instance;
    private String dataPath = "data/";

    private AppConfig() {}

    public static AppConfig getInstance() {
        if (instance == null) instance = new AppConfig();
        return instance;
    }

    public String getDataPath() { return dataPath; }
}