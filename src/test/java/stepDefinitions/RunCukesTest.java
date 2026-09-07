package stepDefinitions;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import utilities.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static utilities.JsonConversion.cs;

@CucumberOptions(features = "classpath:features", plugin = {"pretty", "html:target/cucumber-html-report.html",
        "json:target/cucumber.json"}, tags =
        "@Web", monochrome = true, dryRun = false)

public class RunCukesTest extends AbstractTestNGCucumberTests {

    public static ExtentTest logger;
    public static String tagName = null;
    static ExtentReports extent;

    @BeforeSuite
    public void directoryCleanUp() {
        try {
            String filePath = System.getProperty("user.dir") + File.separator + ConfigReader.getValue("screenshotPath");
            if (new File(filePath).exists()) {
                FileUtils.cleanDirectory(new File(filePath));
            } else {
                FileUtils.forceMkdir(new File(filePath));
            }

            filePath = System.getProperty("user.dir") + File.separator + "Jmeter" + File.separator + "Results";
            if (new File(filePath).exists()) {
                FileUtils.cleanDirectory(new File(filePath));
            } else {
                FileUtils.forceMkdir(new File(filePath));
            }

            filePath = System.getProperty("user.dir") + File.separator + "ExecutionReports" + File.separator
                    + "HTMLReports";
            if (new File(filePath).exists()) {
                FileUtils.cleanDirectory(new File(filePath));
            } else {
                FileUtils.forceMkdir(new File(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void onStart(ITestContext context) {
        try {
            extent = new ExtentReports(System.getProperty("user.dir") + ConfigReader.getValue("extentReportPath"));
            extent.loadConfig(new File(System.getProperty("user.dir") + ConfigReader.getValue("extentConfigPath")));

            //Excel to Json Conversion.
            JsonConversion.jsonConversion(ConfigReader.getValue("AutomationControlSheet"),
                    ConfigReader.getValue("AutomationControlExcelPath"));

            ExcelToCSVConverter.ExcelToCSVInit(ConfigReader.getValue("testDataExcelPath"), "1944");

            //Get all the common setting from excel file that are required for
            GlobalUtil.setCommonSettings(ExcelDataUtil.getCommonSettings());
            String browser = "";
            browser = GlobalUtil.getCommonSettings().getBrowser();
            LogUtil.infoLog(RunCukesTest.class, browser);
            String executionEnv = "";
            executionEnv = GlobalUtil.getCommonSettings().getExecutionEnv();
            String url = "";
            url = GlobalUtil.getCommonSettings().getUrl();

            if (browser == null)
                browser = ConfigReader.getValue("defaultBrowser");
            if (executionEnv == null)
                executionEnv = ConfigReader.getValue("defaultExecutionEnvironment");

            if (url == null) {
                url = ConfigReader.getValue("BASE_URL");
                GlobalUtil.getCommonSettings().setUrl(url);
            }
            LogUtil.infoLog(getClass(),
                    "\n\n+===========================================================================================================+");
            LogUtil.infoLog(getClass(), " Suite started" + " at " + new Date());

            LogUtil.infoLog(getClass(), "Suite Execution For Web application on environment : " + executionEnv);



            // LogUtil.infoLog(getClass(),
            //    "Suite Execution For RestAPI on URL: " + GlobalUtil.getCommonSettings().getRestURL());

            LogUtil.infoLog(getClass(),
                    "\n\n+===========================================================================================================+");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.errorLog(getClass(), "Common Settings not properly set may not run the scripts properly");
        }
    }

    @AfterTest
    public void onFinish(ITestContext context) {
        LogUtil.infoLog(getClass(), " suite finished" + " at " + new Date());
        LogUtil.infoLog(getClass(),
                "\n\n+===========================================================================================================+");
        extent.flush();
        extent.close();
        try {
            String filePathBackup = System.getProperty("user.dir") + File.separator + "ExecutionReports"
                    + File.separator + "HTMLReportsBackup";
            if (new File(filePathBackup).exists()) {

            } else {
                FileUtils.forceMkdir(new File(filePathBackup));
            }
            String filePath = System.getProperty("user.dir") + File.separator + "ExecutionReports" + File.separator
                    + "HTMLReports";
            if (new File(filePath).exists()) {
                File[] listOfFiles = new File(filePath).listFiles();
                if (listOfFiles.length > 0) {
                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            Files.copy(listOfFiles[i], new File(filePathBackup + File.separator + tagName + ".html"));
                        } else if (listOfFiles[i].isDirectory()) {
                        }
                    }
                } else {
                    LogUtil.infoLog(RunCukesTest.class,
                            "Folder present but files will be backed up after 2nd execution.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        KeywordUtil.onExecutionFinish();
    }
}