package com.jorgesaldivar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("webDriverBean")
@ConfigurationProperties(prefix = "webdriver")
public class WebDriverConfig {

	private String name;
	private String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "DriverBean [name=" + name + ", path=" + path + "]";
	}

}
