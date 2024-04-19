package sit707;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class Main {
	
	
    public static void takeScreenshot(WebDriver driver, String screenshotName) {
       
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshot_" + screenshotName + ".png");
        try {
            Files.copy(srcFile.toPath(), destFile.toPath());
            System.out.println("Screenshot taken and saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.officeworks.com.au/app/identity/create-account");

        // Using Relative Locators API to locate elements
        WebElement firstname = driver.findElement(RelativeLocator.with(By.tagName("input")).above(By.id("password")));
        firstname.sendKeys("Ahsan");

        WebElement lastname = driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.id("firstname")));
        lastname.sendKeys("Habib");

        WebElement phoneNumber = driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.id("lastname")));
        phoneNumber.sendKeys("0713442156");

        WebElement email = driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.id("phoneNumber")));
        email.sendKeys("ahsan@gmail.com");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Ahsan@#$123");

        WebElement confirmPassword = driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.id("password")));
        confirmPassword.sendKeys("Ahsan@#$123");

        WebElement createAccountButton = driver.findElement(By.id("account-action-btn"));
        createAccountButton.click();

        takeScreenshot(driver, "registration_form");

        driver.close();
	}
}
