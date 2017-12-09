package br.com.talles.ecommercebooks.selenium.context.sale;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class makeSale extends SaleT {

    @Test
    public void saveSale(){
        // Resume
        browser.get(index);

        WebElement element = browser.findElement(By.id(elements.INPUT_AMOUNT_FIRST));
        element.clear();
        element.sendKeys("3");
        element = browser.findElement(By.id(elements.BUTTON_ADD_CART_FIRST));
        element.click();
        element = browser.findElement(By.id(elements.LINK_CART));
        element.click();

        // Cart
        element = browser.findElement(By.id(elements.LINK_LEFT));
        element.click();

        // Resume
        element = browser.findElement(By.id(elements.INPUT_AMOUNT_SECOND));
        element.clear();
        element.sendKeys("2");
        element = browser.findElement(By.id(elements.BUTTON_ADD_CART_SECOND));
        element.click();
        element = browser.findElement(By.id(elements.LINK_CART));
        element.click();

        // Cart
        element = browser.findElement(By.id(elements.LINK_FINISH));
        element.click();

        // Log-in
        element = browser.findElement(By.id(elements.INPUT_EMAIL));
        element.sendKeys("t@t.t");
        element = browser.findElement(By.id(elements.INPUT_PASSWORD));
        element.sendKeys("Talles@123");
        element = browser.findElement(By.id(elements.BUTTON_LOGIN));
        element.click();

        // Sale
        element = browser.findElement(By.id(elements.LINK_DELIVERY_ADDRESS));
        element.click();

        // DeliveryAddress
        element = browser.findElement(By.id(elements.INPUT_ALIAS));
        element.sendKeys("Emprego");
        element = browser.findElement(By.id(elements.INPUT_OBSERVATION));
        element.sendKeys("Muro muito alto e azul");
        element = browser.findElement(By.id(elements.INPUT_PUBLIC_PLACE_TYPE));
        element.sendKeys("Rua");
        element = browser.findElement(By.id(elements.INPUT_PUBLIC_PLACE));
        element.sendKeys("José Bandeiras Madeiro");
        element = browser.findElement(By.id(elements.INPUT_NUMBER));
        element.sendKeys("269");
        element = browser.findElement(By.id(elements.INPUT_DISTRICT));
        element.sendKeys("Centro");
        element = browser.findElement(By.id(elements.INPUT_POSTAL_CODE));
        element.sendKeys("087.59-550");
        element = browser.findElement(By.id(elements.INPUT_HOME_TYPE));
        element.sendKeys("Apartamento");
        element = browser.findElement(By.id(elements.INPUT_CITY));
        element.sendKeys("Mogi das Cruzes");
        element = browser.findElement(By.id(elements.SELECT_STATE));
        Select select = new Select(element);
        select.selectByValue("8");
        element = browser.findElement(By.id(elements.SELECT_COUNTRY));
        select = new Select(element);
        select.selectByValue("1");
        element = browser.findElement(By.id(elements.BUTTON_FINISH));
        element.click();

        // Sale
        element = browser.findElement(By.id(elements.LINK_CREDIT_CARD));
        element.click();

        // CreditCard
        element = browser.findElement(By.id(elements.INPUT_CARD_NUMBER));
        element.sendKeys("4698.8794.9875.3218");
        element = browser.findElement(By.id(elements.INPUT_PRINTED_NAME));
        element.sendKeys("João Costa");
        element = browser.findElement(By.id(elements.INPUT_SECURITY_CODE));
        element.sendKeys("633");
        element = browser.findElement(By.id(elements.INPUT_EXPIRATION_DATE));
        element.sendKeys("01/01/2025");
        element = browser.findElement(By.id(elements.SELECT_CARD_COMPANY));
        select = new Select(element);
        select.selectByValue("3");
        element = browser.findElement(By.id(elements.BUTTON_FINISH));
        element.click();

        // Sale
        element = browser.findElement(By.id(elements.SELECT_DELIVERY_ADDRESS));
        select = new Select(element);
        select.selectByValue("3");
        element = browser.findElement(By.id(elements.INPUT_PROMOTIONAL_COUPON));
        element.sendKeys("VIRADA2017");
        element = browser.findElement(By.id(elements.BUTTON_VALIDATE_COUPON));
        element.click();
        element = browser.findElement(By.id(elements.INPUT_CREDIT_CARD_FIRST));
        element.clear();
        element.sendKeys("50");
        element = browser.findElement(By.id(elements.INPUT_CREDIT_CARD_SECOND));
        element.clear();
        element.sendKeys("50");
        element = browser.findElement(By.id(elements.INPUT_CREDIT_CARD_THIRD));
        element.clear();
        element.sendKeys("44,74");
        element = browser.findElement(By.id(elements.SELECT_EXCHANGE_COUPON));
        select = new Select(element);
        select.selectByVisibleText("Cupon 2: R$ 69.05");
        element = browser.findElement(By.id(elements.BUTTON_FINISH));
        element.click();

        // Resume
        element = browser.findElement(By.id(elements.LINK_LOGOUT));
        element.click();
        element = browser.findElement(By.id(elements.LINK_LOGIN));
        element.click();

        // Log-in
        element = browser.findElement(By.id(elements.INPUT_EMAIL));
        element.sendKeys("admin@admin.admin");
        element = browser.findElement(By.id(elements.INPUT_PASSWORD));
        element.sendKeys("Admin@123");
        element = browser.findElement(By.id(elements.BUTTON_LOGIN));
        element.click();

        // Dashboard
        element = browser.findElement(By.id(elements.LINK_SALES));
        element.click();

        // Sales
        element = browser.findElement(By.id(elements.LINK_SHOW_FIRST));
        element.click();

        // Show
        element = browser.findElement(By.id(elements.LINK_APPROVED));
        element.click();

        // Sales
        element = browser.findElement(By.id(elements.LINK_SHOW_FIRST));
        element.click();

        // Show
        element = browser.findElement(By.id(elements.LINK_DISPATCH));
        element.click();

        // Sales
        element = browser.findElement(By.id(elements.LINK_LOGOUT));
        element.click();

        // Resume
        element = browser.findElement(By.id(elements.LINK_LOGIN));
        element.click();

        // Log-in
        element = browser.findElement(By.id(elements.INPUT_EMAIL));
        element.sendKeys("t@t.t");
        element = browser.findElement(By.id(elements.INPUT_PASSWORD));
        element.sendKeys("Talles@123");
        element = browser.findElement(By.id(elements.BUTTON_LOGIN));
        element.click();

        // Orders
        element = browser.findElement(By.id(elements.LINK_SHOW_FIRST));
        element.click();

        // Show
        element = browser.findElement(By.id(elements.LINK_RECEIVED));
        element.click();

        // Orders
        element = browser.findElement(By.id(elements.LINK_SHOW_FIRST));
        element.click();

        // Show
        element = browser.findElement(By.id(elements.LINK_EXCHANGE_REQUEST));
        element.click();

        // Exchange
        element = browser.findElement(By.id(elements.INPUT_JUSTIFICATION));
        element.sendKeys("Nao funcionaram comigo, gostaria de ter meu dinheiro de volta!");
        element = browser.findElement(By.id(elements.BUTTON_FINISH));
        element.click();

        // Orders
        element = browser.findElement(By.id(elements.LINK_LOGOUT));
        element.click();

        // Resume
        element = browser.findElement(By.id(elements.LINK_LOGIN));
        element.click();

        // Log-in
        element = browser.findElement(By.id(elements.LINK_LOGIN));
        element = browser.findElement(By.id(elements.INPUT_EMAIL));
        element.sendKeys("admin@admin.admin");
        element = browser.findElement(By.id(elements.INPUT_PASSWORD));
        element.sendKeys("Admin@123");
        element = browser.findElement(By.id(elements.BUTTON_LOGIN));
        element.click();

        // Dashboard
        element = browser.findElement(By.id(elements.LINK_SALES));
        element.click();

        // Sales
        element = browser.findElement(By.id(elements.LINK_SHOW_FIRST));
        element.click();

        // Show
        element = browser.findElement(By.id(elements.LINK_CHANGED_STOCK));
        element.click();

        // Sales
        element = browser.findElement(By.id(elements.LINK_LOGOUT));
        element.click();

        try {
            browser.findElement(By.id(elements.PAGE_RESUME));
        } catch (Exception e) {
            element = null;
        }

        // Redirect list page? YES -> Passed the test!
        Assert.assertNotEquals(null, element);
    }

}
