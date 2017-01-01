package com.jorgesaldivar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("configurationFileBean")
public class ExternalFilesConfig {

	@Value("${screenshot-location}")
	private String screenshotLocation;

	@Value("${spreadsheet-location}")
	private String spreadsheetLocation;

	public String getScreenshotLocation() {
		return screenshotLocation;
	}

	public void setScreenshotLocation(String screenshotLocation) {
		this.screenshotLocation = screenshotLocation;
	}

	public String getSpreadsheetLocation() {
		return spreadsheetLocation;
	}

	public void setSpreadsheetLocation(String spreadsheetLocation) {
		this.spreadsheetLocation = spreadsheetLocation;
	}

	@Override
	public String toString() {
		return "ConfigurationFileBean [screenshotLocation=" + screenshotLocation + ", spreadsheetLocation="
				+ spreadsheetLocation + "]";
	}

}
