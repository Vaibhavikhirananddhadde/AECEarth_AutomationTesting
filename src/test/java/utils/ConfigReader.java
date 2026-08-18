package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis =
                    new FileInputStream("src/test/resources/config.properties");

            properties.load(fis);
            fis.close();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to load config.properties", e
            );
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
