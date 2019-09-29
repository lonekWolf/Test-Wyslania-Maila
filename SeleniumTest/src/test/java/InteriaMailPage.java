
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InteriaMailPage {
    private WebDriver driver;

    public InteriaMailPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnClosePupUp(){
        WebElement popupCloseIcon = driver.findElement(By.className("boardButton boardButton--close"));
        popupCloseIcon.click();
    }

    public void clickOnNewMailBtn(){
        WebElement sendMessageBtn = driver.findElement(By.linkText("Nowa"));
        sendMessageBtn.click();
    }

    public void typingDataAndSendingMessage(String customer, String messageSubject, String messageText ){
        WebElement customerInput = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[5]/div[3]/div[1]/input[1]"));
        WebElement messageSubjectInput = driver.findElement(By.xpath("//input[@placeholder='Temat:']"));
        customerInput.sendKeys(customer);
        messageSubjectInput.sendKeys(messageSubject);
        driver.switchTo().frame("uiTinymce0_ifr");
            WebElement messageTextInput = driver.findElement(By.xpath(("//body[@id='tinymce']")));
            messageTextInput.sendKeys(messageText);
        driver.switchTo().parentFrame();
        WebElement sendMessage = driver.findElement(By.xpath("//button[@class='composition-basic-action composition-basic-action--send']"));
        sendMessage.click();
    }


    public void clickOnSettingsIconAndLogout(){
        WebElement settingsBtn = driver.findElement(By.xpath("//div[@class='contact-user-avatar']"));
        settingsBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='button']")));
        WebElement logoutBtn = driver.findElement(By.xpath("//a[@class='button']"));
        logoutBtn.click();
    }

    public DataFromEmail getDataFromEmailAndDeleteMessage(){
        WebElement message = driver.findElement(By.xpath("//span[@title='kontotestowe25@interia.pl']"));
        message.click();

        DataFromEmail dataFromEmail = new  DataFromEmail();
        dataFromEmail.sender  = driver.findElement(By.xpath("//h2[@class='message-toolbar-users-item message-toolbar-users-from selectable']")).getText();
        dataFromEmail.messageTitle = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/section[3]/div[1]/header[1]/h1[1]/span[1]")).getText();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='message-iframe']")));
            dataFromEmail.messageContent = driver.findElement(By.xpath("/html[1]/body[1]/p[1]")).getText();
        driver.switchTo().parentFrame();
        WebElement deleteMessage = driver.findElement(By.xpath("//a[@class='icon-trash']"));
        deleteMessage.click();
        return  dataFromEmail;
    }

}