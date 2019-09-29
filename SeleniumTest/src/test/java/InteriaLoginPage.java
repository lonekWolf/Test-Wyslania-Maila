import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InteriaLoginPage {
    private WebDriver driver;

    public InteriaLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enteringLoginsDetailsAndLogin(String login, String password){
        WebElement loginInput = driver.findElement(By.id("formEmail"));
        WebElement passwordInput = driver.findElement(By.id("formPassword"));
        WebElement loginBtn = driver.findElement(By.id("formSubmit"));

        loginInput.clear();
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

}