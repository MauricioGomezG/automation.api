package utils;

import dtos.ProfileDTO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileManager {
    private static Properties applicationConfigProperties;
    private static String applicationConfigPropPath = "application.properties";

    public static String getUserConfigProperty(String property) {
        if (null == applicationConfigProperties) {
            applicationConfigProperties = new Properties();

            try {
                InputStream inputStream = ClassLoader.getSystemResourceAsStream(applicationConfigPropPath);

                try {
                    applicationConfigProperties.load(inputStream);
                } catch (Throwable foo) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable foo2) {
                            foo.addSuppressed(foo2);
                        }
                    }

                    throw foo;
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException foo3) {
                throw new RuntimeException("Could not read " + applicationConfigPropPath + " resource file: " + foo3);
            }
        }

        return applicationConfigProperties.getProperty(property);
    }
}