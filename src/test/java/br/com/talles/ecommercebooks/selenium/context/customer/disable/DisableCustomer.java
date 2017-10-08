package br.com.talles.ecommercebooks.selenium.context.customer.disable;

import br.com.talles.ecommercebooks.selenium.context.customer.CustomerT;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DisableCustomer extends CustomerT {
	
	@Test
	public void disableCustomer(){
		// Go to Home page
		browser.get(index);
		
		// Go to List-Custumers page
		WebElement element = browser.findElement(By.className(elements.LINK_LIST));
		element.click();
		
		// Click to delete Customer 1
		element = browser.findElement(By.id(elements.LINK_DISABLE_CUSTOMER_1));
		element.click();
		
		// Redirect list page? YES -> Passed the test!
		Assert.assertNotEquals(null, browser.findElement(By.id(elements.PAGE_LIST)));
	}
	
}
