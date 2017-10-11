package br.com.talles.ecommercebooks.selenium.context.book.save;

import br.com.talles.ecommercebooks.selenium.context.book.BookT;
import org.junit.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BookFilled extends BookT {
	
	@Test
	public void saveCustomer(){
		// Go to Home page
		browser.get(index);
		
		// Go to List-Custumers page
		WebElement element = browser.findElement(By.className(elements.LINK_LIST));
		element.click();
		
		// Go to Create-Customers page
		element = browser.findElement(By.className(elements.LINK_CREATE));
		element.click();
		
		// Set all attributes
		element = browser.findElement(By.id(elements.INPUT_TITLE));
		element.sendKeys("Biblia Sagrada");
		
		element = browser.findElement(By.id(elements.INPUT_SYNOPSIS));
		element.sendKeys("A história da vida");
		
		// Author
		element = browser.findElement(By.id(elements.SELECT_AUTHOR));
		Select select = new Select(element);
		select.selectByValue("2");
		
		// Category
		element = browser.findElement(By.id(elements.SELECT_CATEGORY));
		select = new Select(element);
		select.selectByValue("3");
		
		// PublishingCompany
		element = browser.findElement(By.id(elements.SELECT_PUBLISHING_COMPANY));
		select = new Select(element);
		select.selectByValue("2");
		
		element = browser.findElement(By.id(elements.INPUT_EDITION));
		element.sendKeys("15");
		
		element = browser.findElement(By.id(elements.INPUT_PUBLICATION_YEAR));
		element.clear();
		element.sendKeys("1980");
		
		element = browser.findElement(By.id(elements.INPUT_NUMBER_OF_PAGES));
		element.clear();
		element.sendKeys("1357");
		
		// Dimension
		element = browser.findElement(By.id(elements.INPUT_DIMENSION_HEIGHT));
		element.clear();
		element.sendKeys("23,0");
		
		element = browser.findElement(By.id(elements.INPUT_DIMENSION_WIDHT));
		element.clear();
		element.sendKeys("14,0");
		
		element = browser.findElement(By.id(elements.INPUT_DIMENSION_WEIGHT));
		element.clear();
		element.sendKeys("0,357");
		
		element = browser.findElement(By.id(elements.INPUT_DIMENSION_DEPTH));
		element.clear();
		element.sendKeys("2,0");
		
		element = browser.findElement(By.id(elements.INPUT_ISBN));
		element.sendKeys("1-56619-909-3");
		
		element = browser.findElement(By.id(elements.INPUT_EAN13));
		element.sendKeys("978-1-56619-909-4");
		
		// PriceGroup
		element = browser.findElement(By.id(elements.SELECT_PRICE_GROUP));
		select = new Select(element);
		select.selectByValue("1");
						
		// SaleParameterization
		element = browser.findElement(By.id(elements.INPUT_SALE_PARAMETERIZATION_MIN_SALE_LIMIT));
		element.clear();
		element.sendKeys("15");
		
		element = browser.findElement(By.id(elements.INPUT_SALE_PARAMETERIZATION_PERIODICITY));
		element.clear();
		element.sendKeys("20");
		
		element = browser.findElement(By.id(elements.SELECT_SALE_PARAMETERIZATION_CLASSIFIER_PERIOD));
		select = new Select(element);
		select.selectByValue("D");
		
		// Sent form and save book
		element = browser.findElement(By.id(elements.BUTTON_SAVE_BOOK));
		element.submit();
		
		try {
			browser.findElement(By.id(elements.PAGE_LIST));
		} catch (Exception e) {
			element = null;
		}
		
		// Redirect list page? YES -> Passed the test!
		Assert.assertNotEquals(null, element);
	}
	
}
