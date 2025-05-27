package booksWagon.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

  

    By loginHeader = By.id("ctl00_lblUser");
    By mobileEmailInput = By.id("ctl00_phBody_SignIn_txtEmail");
    By passwordInput = By.id("ctl00_phBody_SignIn_txtPassword");
    By loginBtn = By.id("ctl00_phBody_SignIn_btnLogin");
    By requestOtpBtn = By.id("ctl00_phBody_SignIn_btnRequestOTP");
    By verifyOtpBtn = By.id("ctl00_phBody_SignIn_btnOTP");
    

    
    
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    public void clickLoginHeader() {
    	wait.until(ExpectedConditions.elementToBeClickable(loginHeader)).click();
//        driver.findElement(loginHeader).click();
  }

    public void enterMobileOrEmail(String value) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(mobileEmailInput));
        driver.findElement(mobileEmailInput).clear();
        driver.findElement(mobileEmailInput).sendKeys(value);
    }

    public void enterPassword(String pwd) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(pwd);
    }

    public void clickLoginButton() {
        driver.findElement(loginBtn).click();
    }

    public void clickRequestOtp() {
        driver.findElement(requestOtpBtn).click();
    }

    public void waitForOtpInput() {
        try {
            Thread.sleep(15000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    By userProfile = By.id("ctl00_lblUser");
    By logoutBtn = By.id("ctl00_lnkbtnLogout");

    public void logout1() {
        // Create Actions instance for hover operation
        Actions act = new Actions(driver);

        // Locate elements for hovering & clicking logout
        WebElement profileElement = wait.until(ExpectedConditions.presenceOfElementLocated(userProfile));
        WebElement logoutElement = wait.until(ExpectedConditions.presenceOfElementLocated(logoutBtn));

        // Perform hover and click logout
        act.moveToElement(profileElement).moveToElement(logoutElement).click().perform();

        System.out.println("User logged out successfully.");
    }


    
    
    
    public void clickVerifyOtp() {
        driver.findElement(verifyOtpBtn).click();
    }



 
    
    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("myaccount.aspx");
    }


}

