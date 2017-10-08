package br.com.talles.ecommercebooks.selenium.context.customer.save;

import br.com.talles.ecommercebooks.selenium.context.customer.CustomerT;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WithoutDeliveryAddress extends CustomerT {
	
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
		
		// Get all attributes
		element = browser.findElement(By.id(elements.INPUT_REGISTRY));
		element.sendKeys("889.151.254-01");
		
		element = browser.findElement(By.id(elements.INPUT_NAME));
		element.sendKeys("Miguel Marcos de Andrade");
		
		element = browser.findElement(By.id(elements.INPUT_BIRTH_DATE));
		element.sendKeys("20/05/1990");
		
		element = browser.findElement(By.id(elements.INPUT_GENDER_MALE));
		element.click();
		
		// Phone
		element = browser.findElement(By.id(elements.INPUT_PHONE_DDD));
		element.sendKeys("11");
		
		element = browser.findElement(By.id(elements.INPUT_PHONE_NUMBER));
		element.sendKeys("4685-9788");
		
		element = browser.findElement(By.id(elements.INPUT_PHONE_TYPE));
		element.sendKeys("Fixo");
		
		// User
		element = browser.findElement(By.id(elements.INPUT_USER_EMAIL));
		element.sendKeys("miguel_andrade@email.com.br");
		
		element = browser.findElement(By.id(elements.INPUT_USER_PASSWORD));
		element.sendKeys("abc123");
		
		element = browser.findElement(By.id(elements.INPUT_USER_PASSWORD_VERIFY));
		element.sendKeys("abc123");
		
		// Credit Card
		element = browser.findElement(By.id(elements.INPUT_CARD_NUMBER));
		element.sendKeys("4568.8885.6972.3201");
		
		element = browser.findElement(By.id(elements.INPUT_CARD_PRINTED_NAME));
		element.sendKeys("Miguel Andrade");
		
		element = browser.findElement(By.id(elements.INPUT_CARD_SECURITY_CODE));
		element.sendKeys("577");
		
		element = browser.findElement(By.id(elements.INPUT_CARD_EXPIRATION_DATE));
		element.sendKeys("01/12/2020");
		
		element = browser.findElement(By.id(elements.INPUT_CARD_CARD_COMPANY));
		Select select = new Select(element);
		select.selectByValue("2");
		
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
