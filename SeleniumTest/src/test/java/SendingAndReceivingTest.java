import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class SendingAndReceivingTest {
    private WebDriver driver;
    private String loginToFirstAcount = "kontotestowe25";
    private String passwordToFirstAcount = "Plemion@12";
    private String loginToSecondAcount = "kontotestowe26";
    private String passwordToSecondAcount = passwordToFirstAcount;
    private String recipientMessage = "kontotestowe26@interia.pl";
    private String titleToCheck = "Test poprawnego wysłania mail'a";
    private String messageToCheck = "Wiadomość do przetestowania";
    private String messageSender = "krzysztof Kowalski";


    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.manage().window().maximize();
    }


    @Test
    public void Test(){
        driver.navigate().to("https://www.interia.pl/");

        InteriaPage interiaPage = new InteriaPage(driver);
        interiaPage.acceptRodo();
        interiaPage.mailTabClick();

        InteriaLoginPage interiaLoginPage = new InteriaLoginPage(driver);
        interiaLoginPage.enteringLoginsDetailsAndLogin(loginToFirstAcount, passwordToFirstAcount);

        InteriaMailPage interiaMailPage = new InteriaMailPage(driver);
        //interiaMailPage.clickOnClosePupUp();
        interiaMailPage.clickOnNewMailBtn();
        interiaMailPage.typingDataAndSendingMessage(recipientMessage, titleToCheck, messageToCheck);
        interiaMailPage.clickOnSettingsIconAndLogout();

        //w przypadku gdy po wylogowaniu pojawia się reklama na stronie Interii
        interiaPage.clickOnCloseIcon();
        interiaPage.mailTabClick();

        interiaLoginPage.enteringLoginsDetailsAndLogin(loginToSecondAcount, passwordToSecondAcount);
        DataFromEmail dataFromEmail;
        dataFromEmail = interiaMailPage.getDataFromEmailAndDeleteMessage();

        assertEquals(messageSender, dataFromEmail.sender);
        assertEquals(titleToCheck, dataFromEmail.messageTitle);
        assertEquals(messageToCheck, dataFromEmail.messageContent);
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
        driver.quit();
    }
}
