package com.jorgesaldivar.actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.jorgesaldivar.config.ExternalFilesConfig;

public class WebDriverActions {

	private WebDriver driver;
	private ExternalFilesConfig externalFilesConfig;
	private static final Logger logger = Logger.getLogger(WebDriverActions.class);

	public WebDriverActions(WebDriver driver, ExternalFilesConfig externalFilesConfig) {

		this.driver = driver;
		this.externalFilesConfig = externalFilesConfig;

	}

	public void goToAmazon() throws InterruptedException {

		driver.get("http://www.amazon.com");

	}

	public void searchAmazon(String item) {

		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(item);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		logger.info(driver.findElement(By.id("s-result-count")).getText().replaceAll("\n", " "));

	}

	public void takeScreenshot() {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(externalFilesConfig.getScreenshotLocation() + dateTimeNow() + ".png"));
		} catch (IOException e) {
			logger.error("Unable to save image - stacktrace: " + e);
		}

	}

	public String dateTimeNow() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH_mm_ss");
		Date date = new Date();
		return sdf.format(date);

	}

}
