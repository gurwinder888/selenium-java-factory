package utilities;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonConversion {

    public static CommonSettings cs = new CommonSettings();

    private static List<JSONObject> dataList;

    public static void gsonToJson(List<JSONObject> dataList) {
        Gson gson = new Gson();

        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir") + "/src/test/resources/JsonData/" + "excel.json");
            file.write(gson.toJson(dataList));
            file.close();
        } catch (Exception e) {
            System.out.println("Error occurred : " + e);
            e.printStackTrace();
            Assert.fail();
        }
    }

    public static void parsingJsonData(String path) {
        try {
            JSONParser parser = new JSONParser();

            //Generally generated json is in array form.
            JSONArray a = (JSONArray) parser.parse(new FileReader(path));

            for (Object o : a) {
                JSONObject person = (JSONObject) o;
                // Config entries parsing can be handled here for web settings
            }
        } catch (Exception e) {
            System.out.println("Error occurred : " + e);
            e.printStackTrace();
            Assert.fail();
        }
    }

    public static void jsonConversion(String sheetName, String worksheet) {
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + worksheet);
             Workbook wb = WorkbookFactory.create(fis)) {
            if (wb.getSheetIndex(wb.getSheet(sheetName)) == -1) {
                System.out.println("Invalid sheet name");
            }
            Sheet sheet = wb.getSheet(sheetName);
            JSONObject rowJsonObject = new JSONObject();

            //For Android config for now...
            for (int i = 43; i < 47; i++) {
                Row row = sheet.getRow(i);
                String column = row.getCell(0).toString();
                String value = row.getCell(1).toString();
                rowJsonObject.put(column, value);
            }
            dataList = new ArrayList<>();
            dataList.add(rowJsonObject);

            //JsonObject to gson to JSON
            gsonToJson(dataList);

            //Fetching json data.
            parsingJsonData(System.getProperty("user.dir") + "/src/test/resources/JsonData/excel.json");
        } catch (Exception e) {
            System.out.println("Error occurred : " + e);
            e.printStackTrace();
            Assert.fail();
        }
    }
}
