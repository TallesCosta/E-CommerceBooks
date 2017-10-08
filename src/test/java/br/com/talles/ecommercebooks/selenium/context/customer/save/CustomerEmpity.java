package br.com.talles.ecommercebooks.selenium.context.customer.save;

import br.com.talles.ecommercebooks.selenium.context.customer.CustomerT;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CustomerEmpity extends CustomerT {
	
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
		
		// Sent form and save customer
		element = browser.findElement(By.id(elements.BUTTON_SAVE_CUSTOMER));
		element.submit();
		
		try {
			browser.findElement(By.id(elements.PAGE_CREATE));
		} catch (Exception e) {
			element = null;
		}
		
		// Redirect list page? YES -> Passed the test!
		Assert.assertNotEquals(null, element);
	}
	
}
