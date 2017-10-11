package br.com.talles.ecommercebooks.selenium.context.book.save;

import br.com.talles.ecommercebooks.selenium.context.book.BookT;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BookEmpity extends BookT {
	
	@Test
	public void saveBook(){
		// Go to Home page
		browser.get(index);
		
		// Go to List-Books page
		WebElement element = browser.findElement(By.className(elements.LINK_LIST));
		element.click();
		
		// Go to Create-Books page
		element = browser.findElement(By.className(elements.LINK_CREATE));
		element.click();
		
		// Sent form and save book
		element = browser.findElement(By.id(elements.BUTTON_SAVE_BOOK));
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
