package br.com.talles.ecommercebooks.selenium.context.customer.list;

import br.com.talles.ecommercebooks.selenium.context.customer.CustomerT;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ListCustomer extends CustomerT {
	
	@Test
	public void saveCustomer(){
		// Go to Home page
		browser.get(index);
		
		// Go to List-Custumers page
		WebElement element = browser.findElement(By.className(elements.LINK_LIST));
		element.click();
		
		try {
			browser.findElement(By.id(elements.PAGE_LIST));
		} catch (Exception e) {
			element = null;
		}
		
		// Redirect list page? YES -> Passed the test!
		Assert.assertNotEquals(null, element);
	}
	
}
