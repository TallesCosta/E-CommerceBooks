package br.com.talles.ecommercebooks.selenium;

import org.openqa.selenium.WebDriver;

import br.com.talles.ecommercebooks.config.PropertiesConfig;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class ApplicationTest {

	protected WebDriver browser;
	
	public ApplicationTest() {
		PropertiesConfig webDriverConfig = new PropertiesConfig();
        String chormePath = webDriverConfig.getWebDriverChromePath();
				
		System.setProperty("webdriver.chrome.driver", chormePath);
	}
	
	@Before
	public void openBrowser(){
		browser = new ChromeDriver();	
	}
	
	@After
	public void closeBrowser(){
		browser.quit();
	}
	
}
