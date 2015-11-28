package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * User: RonaldButron
 * Date: 11/27/15
 */
public class ConfigFileReader {

    private String  returnValue = null;

    public ConfigFileReader(){

    }

    public String getPropertiesValues(String value) {


        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);
            returnValue = prop.getProperty(value);
            System.out.print(returnValue);

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
        return returnValue;
    }
}



