package stepDefinitions.WWPWeb;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import constants.web.Constant;
import utilities.DriverUtil;
import utilities.ExcelDataUtil;
import utilities.GlobalUtil;
import utilities.KeywordUtil;

import java.util.HashMap;

public class Login {

    public static HashMap<String, String> dataMap = new HashMap<String, String>();

    @Given("Read the testdata {string} from excel file")
    public void readTheTestdataFromExcelFile(String arg1) {
        dataMap = ExcelDataUtil.getTestDataWithTestCaseID("Nykaa", arg1);

    }

    @And("User navigates to the url")
    public void userNavigatesToTheUrl() {

        GlobalUtil.setDriver(DriverUtil.invokeLocalBrowserWeb(Constant.ENVIRONMENT, Constant.BROWSER));
        KeywordUtil.navigateToUrl(dataMap.get("URL"));

    }
}
