import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IntroductionLog {

    private WebDriver driver;
    private String baseUrl = "https://dev-uwd.enterra.de/web/uwd/login";
    
    @BeforeClass
    public void setUp() {
        // Set the path to the ChromeDriver executable
    	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chirag\\Documents\\chromedriver.exe");
        
        // Instantiate ChromeDriver
        driver = new ChromeDriver();
        
        // Navigate to the website
        driver.get(baseUrl);
    }

    @Test(priority = 1)
    public void validLoginTest() {
        // Locate the username and password input fields
   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement usernameInput = driver.findElement(By.xpath("//input[@id='_com_liferay_login_web_portlet_LoginPortlet_login']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='_com_liferay_login_web_portlet_LoginPortlet_password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[starts-with (@id,'_com_liferay_login_web_portlet_LoginPortlet')]"));

        // Enter valid credentials
        usernameInput.sendKeys("cr@enterra.de");
        passwordInput.sendKeys("Chirag#12");

        // Click the login button
        loginButton.click();

    
        // Assert the successful login
   String actualTitle = driver.getTitle();
    Assert.assertEquals(actualTitle, "Login - UWD Startseite - Unternehmerische Zukunft gestalten - Unternehmenswerkstatt Deutschland");
        

        
        
    }

    @Test(priority = 2)
    public void invalidLoginTest() {
        // Locate the username and password input fields
    	
    	  WebElement usernameInput = driver.findElement(By.xpath("//input[@id='_com_liferay_login_web_portlet_LoginPortlet_login']"));
          WebElement passwordInput = driver.findElement(By.xpath("//input[@id='_com_liferay_login_web_portlet_LoginPortlet_password']"));
          WebElement loginButton = driver.findElement(By.xpath("//button[starts-with (@id,'_com_liferay_login_web_portlet_LoginPortlet')]"));
          //Clear Textbox
          usernameInput.clear();
          passwordInput.clear();
        // Enter invalid credentials
        usernameInput.sendKeys("chirag");
        passwordInput.sendKeys("Test@12345");

        // Click the login button
        loginButton.click();

        // Assert the presence of an error message
        WebElement errorMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        Assert.assertTrue(errorMessage.isDisplayed(), " Authentication failed.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}