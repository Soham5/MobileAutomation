package UserDefinedLibraries;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Properties;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class DriverSetup {

    private static AppiumDriver driver;
    private static String appPath;
    private static Properties prop              =   PropertiesFile.getPropertiesInstance();
    private static Boolean useRemoteDriver      =   Boolean.valueOf(prop.getProperty("useRemoteDriver"));
    private static String deviceType;

    public static AppiumDriver driverInstantiate(String device) throws MalformedURLException {
        deviceType=device.toLowerCase(Locale.ROOT);
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 3");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.UDID,"emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP , appPath);

        URL url = new URL("appium server url");

        driver =new AppiumDriver(url,capabilities);


        if(useRemoteDriver) {
            switch (deviceType) {
//                case "chrome":
//                    exePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\chromedriver.exe";
//                    System.setProperty("webdriver.chrome.driver", exePath);
//                    driver = new ChromeDriver();
//                    break;
            }
        }
        else {
            switch (deviceType) {
                case "android":
                    appPath = System.getProperty("user.dir") + "\\src\\main\\resources\\app\\chromedriver.apk";
                    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
                    capabilities.setCapability(MobileCapabilityType.APP , appPath);
                    driver= new AndroidDriver(url,capabilities);
                    break;

                case "ios":
                    appPath = System.getProperty("user.dir") + "\\src\\main\\resources\\app\\chromedriver.apk";
                    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"IOS");
                    capabilities.setCapability(MobileCapabilityType.APP , appPath);
                    driver= new IOSDriver(url,capabilities);
                    break;
            }
        }

        return driver;
    }
}
