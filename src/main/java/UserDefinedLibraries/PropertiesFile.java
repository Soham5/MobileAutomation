package UserDefinedLibraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

    private static Properties globalProps;
    private static String path      =   System.getProperty("user.dir")+"\\src\\main\\resources\\Properties\\PropertiesConfig.properties";

    public static Properties getPropertiesInstance() {
        if(globalProps==null) {
            globalProps= new Properties();
            try {
                FileInputStream file = new FileInputStream(path);
                globalProps.load(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return globalProps;
    }

}
