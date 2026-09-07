package utilities;

import org.openqa.selenium.WebDriver;

/**
 * This class will get date and time, and it will rename the file with date and
 * time
 */
public class GlobalUtil {

    /**
     * The constant errorMsg.
     */
    public static String errorMsg;
    /**
     * The constant e.
     */
    public static Throwable e;
    private static CommonSettings commonSettings = new CommonSettings();

    private static WebDriver webDriver = null;
    private static WebDriver Driver;

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        GlobalUtil.webDriver = webDriver;
    }

    public static WebDriver getDriver() {
        return Driver;
    }

    public static void setDriver(WebDriver driver) {
        Driver = driver;
    }

    /**
     * Gets common settings.
     *
     * @return common settings
     */
    public static CommonSettings getCommonSettings() {
        return commonSettings;
    }

    /**
     * Sets common settings.
     *
     * @param commonSettings the common settings
     */
    public static void setCommonSettings(CommonSettings commonSettings) {
        GlobalUtil.commonSettings = commonSettings;
    }
}

