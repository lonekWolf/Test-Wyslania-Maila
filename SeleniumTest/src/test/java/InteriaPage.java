import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

public class InteriaPage {
    private WebDriver driver;

    public InteriaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptRodo(){
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Interia - Polska i świat: informacje, sport, gwiazdy."));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement rodoAccept = driver.findElement(By.className("rodo-popup-agree"));
        rodoAccept.click();
    }

    public void mailTabClick(){
        WebElement pocztaBtn = driver.findElement(By.xpath("//a[@class='main-links-a switch-mail']"));
        pocztaBtn.click();
    }

    public void clickOnCloseIcon(){
        WebElement popupClose = driver.findElement(By.className("ad-close"));
        popupClose.click();
    }


}
