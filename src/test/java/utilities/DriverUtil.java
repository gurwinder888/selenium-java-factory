package utilities;

import io.github.bonigarcia.wdm.OperatingSystem;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * The type Driver util.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverUtil {

    private static final Map<String, WebDriver> drivers = new HashMap<>();

    public static String testName;

    //Web Browser methods

    /**
     * Invoke local browser web driver.
     *
     * @param osName      the os name
     * @param browserName the browser name
     * @return the web driver
     */
    public static WebDriver invokeLocalBrowserWeb(String osName, String browserName) {
        WebDriver browser = null;
        OperatingSystem operatingSystem;
        try {
            osName = osName.toUpperCase();
            browserName = browserName.toUpperCase();

            switch (osName) {
                case "MAC":
                    operatingSystem = OperatingSystem.MAC;
                    break;
                case "WINDOWS":
                    operatingSystem = OperatingSystem.WIN;
                    break;
                case "LINUX":
                    operatingSystem = OperatingSystem.LINUX;
                    break;
                default:
                    throw new Exception("Invalid OS Name - " + osName);
            }

            browser = drivers.get(browserName);
            if (browser == null)
                switch (browserName) {
                    case "CHROME":
                        WebDriverManager.chromedriver().operatingSystem(operatingSystem).setup();
                        browser = new ChromeDriver();
                        break;
                    case "FIREFOX":
                        WebDriverManager.firefoxdriver().operatingSystem(operatingSystem).setup();
                        browser = new FirefoxDriver();
                        break;
                    default:
                        throw new Exception("Invalid Browser Name - " + browserName);
                }
            drivers.put(browserName, browser);
            browser.manage().window().maximize();
            browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            LogUtil.errorLog(DriverUtil.class, "Browser not launched. Please check the configuration ", e);
            e.printStackTrace();
        }
        return browser;
    }

    /**
     * Invoke sauce labs browser web driver.
     *
     * @param browserName    the browser name
     * @param osName         the os name
     * @param browserVersion the browser version
     * @param buildId        the build id
     * @param testName       the test name
     * @return the web driver
     */
    public static WebDriver invokeSauceLabsBrowserWeb(String browserName, String osName, String browserVersion, String buildId, String testName) {
        WebDriver browser = null;
        String URL;
        browserName = browserName.split("_")[0].toUpperCase();
        DesiredCapabilities desiredCapabilities;

        try {
            switch (browserName) {
                case "CHROME":
                    desiredCapabilities = DesiredCapabilities.chrome();
                    break;
                case "FIREFOX":
                    desiredCapabilities = DesiredCapabilities.firefox();
                    break;
                case "IE":
                    desiredCapabilities = DesiredCapabilities.internetExplorer();
                    break;
                default:
                    throw new Exception("Invalid Browser Name - " + browserName);
            }

            desiredCapabilities.setCapability("platform", osName);
            desiredCapabilities.setCapability("version", browserVersion);
            desiredCapabilities.setCapability("build", buildId);
            desiredCapabilities.setCapability("name", testName);
            desiredCapabilities.setCapability("extendedDebugging", "true");

            URL = "https://" + GlobalUtil.getCommonSettings().getHostName() + ":" + GlobalUtil.getCommonSettings().getKey() + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
            browser = new RemoteWebDriver(new URL(URL), desiredCapabilities);

            drivers.put(browserName, browser);

        } catch (Exception e) {
            LogUtil.errorLog(DriverUtil.class, "Browser not launched. Please check the configuration ", e);
            e.printStackTrace();
        }

        return browser;
    }

    /**
     * Gets browser.
     *
     * @param exeEnv the exe env
     * @return the browser
     */
    public static WebDriver getBrowser(String exeEnv) {

        WebDriver browser;
        String browserName;
        String osName;

        if (exeEnv.equalsIgnoreCase("Remote")) {

            browserName = GlobalUtil.getCommonSettings().getBrowser().split("_")[0];
            String browserVersion = GlobalUtil.getCommonSettings().getBrowser().split("_")[1];
            osName = GlobalUtil.getCommonSettings().getRemoteOS();
            String buildId = "Build 1";
            String testName = "Web Test Dummy 1";

            browser = invokeSauceLabsBrowserWeb(browserName, osName, browserVersion, buildId, testName);

            LogUtil.infoLog(DriverUtil.class, String.format("Browser launched on Remote - Browser = %s, OS = %s, Browser Version = %s, Build Name = %s, " + "Test Name = %s", browserName, osName, browserVersion, buildId, testName));
        } else {

            browserName = GlobalUtil.getCommonSettings().getBrowser().split("_")[0];
            osName = GlobalUtil.getCommonSettings().getRemoteOS();

            browser = invokeLocalBrowserWeb(osName, browserName);

            LogUtil.infoLog(DriverUtil.class, String.format("Browser launched on Local - Browser = %s, OS = %s", browserName, osName));
        }
        return browser;
    }

    /**
     * Close all driver.
     */
    public static void closeAllDriver() {

        drivers.entrySet().forEach(key -> {
            key.getValue().quit();
            key.setValue(null);
        });

        LogUtil.infoLog(DriverUtil.class, "Closing Browsers");
    }

    /**
     * Gets img ref.
     *
     * @param imgFile the img file
     * @return the img ref
     */
    public static String getImgRef(String imgFile) {
        return new DriverUtil().getRefImage(imgFile);
    }

    private String getRefImage(String imgFile) {
        String openCVImgsFolder = "OpenCVImages/";
        URL refImgUrl = getClass().getClassLoader().getResource(openCVImgsFolder + imgFile + ".png");
        File refImgFile;
        try {
            refImgFile = Paths.get(refImgUrl.toURI()).toFile();
            LogUtil.infoLog(DriverUtil.class, "File Found : " + refImgFile.exists());
            return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}