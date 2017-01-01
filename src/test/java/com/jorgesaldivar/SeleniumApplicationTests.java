package com.jorgesaldivar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import com.jorgesaldivar.actions.WebDriverActions;
import com.jorgesaldivar.config.ExternalFilesConfig;
import com.jorgesaldivar.config.WebDriverConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeleniumApplicationTests {

	private WebDriver driver;
	private WebDriverActions webDriverActions;
	private ArrayList<String> searchList = new ArrayList<String>();
	private static final Logger logger = LoggerFactory.getLogger(SeleniumApplicationTests.class);

	@Autowired
	private WebDriverConfig webDriverConfig;

	@Autowired
	private ExternalFilesConfig externalFilesConfig;

	@Before
	public void setup() {
		System.setProperty(webDriverConfig.getName(), webDriverConfig.getPath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		webDriverActions = new WebDriverActions(driver, externalFilesConfig);
	}

	@After
	public void terminate() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

	@Test
	public void searchAmazonItems()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		readFile("search");

		for (String item : searchList) {
			webDriverActions.goToAmazon();
			webDriverActions.searchAmazon(item);
		}

	}

	@Test
	public void takeWebsiteScreenshot() throws InterruptedException {
		webDriverActions.goToAmazon();
		webDriverActions.takeScreenshot();
	}

	@Test
	@Repeat(2)
	@Ignore
	public void takeScreenshotPerItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		takeWebsiteScreenshot();

	}

	public void readFile(String value) throws IOException, EncryptedDocumentException, InvalidFormatException {

		Workbook wb = WorkbookFactory.create(new File(externalFilesConfig.getSpreadsheetLocation()));
		Sheet mySheet = wb.getSheetAt(0);
		searchList.clear();

		int lastColumn = mySheet.getRow(0).getLastCellNum();
		int position = 0;

		// GET CELL POSITION based on Value
		for (int i = 0; i < lastColumn; i++) {
			logger.info(mySheet.getRow(0).getCell(i).getStringCellValue());
			if (mySheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(value)) {
				position = i;
				break;
			}
		}

		// ALL SHEET
		int lastRow = mySheet.getLastRowNum();
		int positionBasedOnCell = 0;

		// GET LAST ROW FROM THAT CELL POSITION
		for (int i = 1; i <= lastRow; i++) {
			try {
				logger.info(mySheet.getRow(i).getCell(position).getStringCellValue());
				positionBasedOnCell = i;
			} catch (NullPointerException e) {
				break;
			}
		}

		for (int i = 1; i <= positionBasedOnCell; i++) {
			searchList.add(mySheet.getRow(i).getCell(position).getStringCellValue());
		}

	}

}
