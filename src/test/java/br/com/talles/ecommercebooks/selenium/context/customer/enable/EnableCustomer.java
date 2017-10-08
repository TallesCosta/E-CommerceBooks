package br.com.talles.ecommercebooks.selenium.context.customer.enable;

import br.com.talles.ecommercebooks.selenium.context.customer.CustomerT;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EnableCustomer extends CustomerT {

	@Test
	public void disableCustomer(){
		// Go to Home page
		browser.get(index);
		
		// Go to List-Disable-Custumers page
		WebElement element = browser.findElement(By.className(elements.LINK_LIST_DISABLE));
		element.click();
		
		// Click to enable Customer 1
		element = browser.findElement(By.id(elements.LINK_ENABLE_CUSTOMER_1));
		element.click();
		
		try {
			browser.findElement(By.id(elements.PAGE_LIST_DISABLE));
		} catch (Exception e) {
			element = null;
		}
		
		// Redirect list page? YES -> Passed the test!
		Assert.assertNotEquals(null, element);
	}
	
}
