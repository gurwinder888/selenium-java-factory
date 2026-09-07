package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

/**
 * The type Html report util.
 */
public class HTMLReportUtil {

    /**
     * The constant DummyString.
     */
    public static String DummyString;
    /**
     * The Html.
     */
    static String html;

    /**
     * Test fail take screenshot string.
     *
     * @param imagePath the image path
     * @return the string
     * @throws IOException the io exception
     */
    public static String testFailTakeScreenshot(String imagePath) throws IOException {
        File src = ((TakesScreenshot) GlobalUtil.getDriver()).getScreenshotAs(OutputType.FILE);
        File des = new File(imagePath);
        FileUtils.copyFile(src, des);
        LogUtil.infoLog(HTMLReportUtil.class, des.toString());
        DummyString = des.getAbsolutePath();
        String path = DummyString;
        String base = "TXAutomate/ExecutionReports/FailedScreenshots/";
        return new File(base).toURI().relativize(new File(path).toURI()).getPath();
    }



    /**
     * Fail string red color string.
     *
     * @param stepName the step name
     * @return the string
     */
    public static String failStringRedColor(String stepName) {
        html = "<span style='color:red'><b>" + stepName + "</b></span>";
        return html;
    }

    /**
     * Info string blue color string.
     *
     * @param stepName the step name
     * @return the string
     */
    public static String infoStringBlueColor(String stepName) {
        html = "<span style='color:blue'><b>" + stepName + "</b></span>";
        return html;
    }

    /**
     * Pass string green color string.
     *
     * @param stepName the step name
     * @return the string
     */
    public static String passStringGreenColor(String stepName) {
        html = "<span style='color:#008000'><b>" + stepName + " - PASSED" + "</b></span>";
        return html;
    }

}
