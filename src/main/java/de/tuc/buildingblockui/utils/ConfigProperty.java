package de.tuc.buildingblockui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperty 
{
    private static int numberOfGridColumns, numberOfGridRows;

    public static int getNumberOfGridColumns() 
    {
        return numberOfGridColumns;
    }

    public static int getNumberOfGridRows() 
    {
        return numberOfGridRows;
    }
    
    public static void readProperty()
    {
        Properties prop = new Properties();
	InputStream input = null;

	try 
        {
            input = new FileInputStream("src/main/resources/config.properties");
            /* load a properties file */
            prop.load(input);
            /* get the property value and print it out */
            numberOfGridRows = Integer.parseInt(prop.getProperty("numberOfGridRows"));
            numberOfGridColumns = Integer.parseInt(prop.getProperty("numberOfGridColumns"));
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

    }
}
