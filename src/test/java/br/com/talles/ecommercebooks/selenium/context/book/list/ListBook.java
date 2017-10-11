package br.com.talles.ecommercebooks.selenium.context.book.list;

import br.com.talles.ecommercebooks.selenium.context.book.BookT;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ListBook extends BookT {
	
	@Test
	public void listCustomer(){
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
