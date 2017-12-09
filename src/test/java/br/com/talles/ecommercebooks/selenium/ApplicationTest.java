package br.com.talles.ecommercebooks.selenium;

import br.com.talles.ecommercebooks.config.PropertiesConfig;

import org.openqa.selenium.WebDriver;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class ApplicationTest {

	protected WebDriver browser;
	protected String index;
	
	public ApplicationTest() {
		PropertiesConfig webDriverConfig = new PropertiesConfig();
        String chormePath = webDriverConfig.getWebDriverChromePath();
		
		System.setProperty("webdriver.chrome.driver", chormePath);
		
		index = "http://localhost:8080/E-CommerceBooks/stocks/list?operation=LIST";
	}
	
	@Before
	public void openBrowser(){
		browser = new ChromeDriver();	
	}
	
	/*@After
	public void closeBrowser(){
		browser.quit();
	}*/
	
}
