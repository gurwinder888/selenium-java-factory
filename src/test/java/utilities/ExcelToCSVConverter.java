package utilities;

import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.*;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utilities.JsonConversion.cs;

public class ExcelToCSVConverter {

    private String csvPath;

    public static void ExcelToCSVInit(String path, String userId) {
        ExcelToCSVConverter app = new ExcelToCSVConverter();
        try (InputStream inp = new FileInputStream(path)) {
            //WorkbookFactory.create will generate an appropriate workbook by auto-detecting extension of the workbook.
            Workbook wb = WorkbookFactory.create(inp);

            //Will generate multi-Csv on the basis of the sheets in a particular workbook.
            //for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            //    System.out.println(wb.getSheetAt(i).getSheetName());
            //    app.convertExcelToCSV(wb.getSheetAt(i), wb.getSheetAt(i).getSheetName());
            //}

            app.convertExcelToCSV(wb.getSheet("AgUsers"), "ArtistGrowthUsers");

            app.parsingCsvValues(userId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void parsingCsvValues(String userId) {
        try {
            // Create an object of fileReader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(csvPath);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            ArrayList<String> x = new ArrayList<>();

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    x.add(cell);
                    //System.out.print(cell + "\t");
                }
                x.removeIf(String::isEmpty);
            }

            cs.setEmail(x.get(x.indexOf(userId) + 1));
            cs.setPassword(x.get(x.indexOf(userId) + 2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertExcelToCSV(Sheet sheet, String sheetName) {
        StringBuilder data = new StringBuilder();
        try {
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                        data.append(cell.getBooleanCellValue());
                    } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        data.append(cell.getNumericCellValue());
                    } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String cellValue = cell.getStringCellValue();
                        if (!cellValue.isEmpty()) {
                            cellValue = cellValue.replaceAll("\"", "\"\"");
                            data.append("\"").append(cellValue).append("\"");
                        }
                    } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                        //LogUtil.infoLog(getClass(), "Cell is Empty");
                    } else {
                        data.append(cell + "");
                    }
                    if (cell.getColumnIndex() != row.getLastCellNum() - 1) {
                        data.append(",");
                    }
                }
                data.append('\n');
            }

            csvPath = System.getProperty("user.dir") + "/src/test/resources/CsvFiles/" + sheetName + ".csv";
            Files.write(Paths.get(csvPath),
                    data.toString().getBytes("UTF-8"));
            LogUtil.infoLog(getClass(), "Generated CSV file is added to : " + csvPath);
        } catch (Exception e) {
            LogUtil.errorLog(getClass(), "Error occurred while conversion of Excel to CSV : " + e);
            e.printStackTrace();
            Assert.fail();
            //.
        }
    }
}
