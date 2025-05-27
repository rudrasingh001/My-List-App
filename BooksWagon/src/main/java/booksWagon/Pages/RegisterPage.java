package booksWagon.Pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    WebDriver driver;
    WebDriverWait wait;

    

    // Locators
//    private By registerHeader = By.xpath("/html/body/form/header/div[1]/div/div[3]/ul/li[1]/div/div/div[2]/a[2]");
    private By nameField = By.id("ctl00_phBody_SignUp_txtName");
    private By mobileField = By.id("ctl00_phBody_SignUp_txtEmail");
    private By continueButton = By.id("ctl00_phBody_SignUp_btnContinue");

    private By otpField = By.id("ctl00_phBody_SignUp_txtOTP");
    private By passwordField = By.id("ctl00_phBody_SignUp_txtPassword");
    private By confirmPasswordField = By.id("ctl00_phBody_SignUp_txtConfirmPwd");
    private By registerButton = By.id("ctl00_phBody_SignUp_btnSubmit");

    private By successIndicator = By.id("privacypolicy");
    private By errorMessageLabel = By.cssSelector(".error");  // Adjust if actual selector differs
    
    
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Methods
 
//    
//    public void clickRegisterHeader() {
//        WebElement ele1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_lblUser"))); 
//        WebElement ele2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='themecolor headersignuplink']")));
//
//        Actions act = new Actions(driver);
//        act.moveToElement(ele1).moveToElement(ele2).click().perform();
//    }
//    
    
	public  void clickRegisterHeader() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions act = new Actions(driver);
		WebElement ele1 = driver.findElement(By.id("ctl00_lblUser"));
		WebElement ele2 = driver.findElement(By.xpath("//a[@class='themecolor headersignuplink']"));
		act.moveToElement(ele1).moveToElement(ele2).click().perform();

	}


    public void enterName(String name) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterMobile(String mobile) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(mobileField));
        driver.findElement(mobileField).clear();
        driver.findElement(mobileField).sendKeys(mobile);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public void enterPassword(String password) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        driver.findElement(confirmPasswordField).clear();
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }
    
//    public boolean isErrorMessageDisplayed() {
//        return !driver.findElements(By.cssSelector(".error")).isEmpty();
//    }
    
    public boolean isErrorMessageDisplayed() {
        try {
            return driver.findElement(By.id("ctl00_phBody_SignUp_rfvName")).isDisplayed() ||
                   driver.findElement(By.id("ctl00_phBody_SignUp_rfvEmail")).isDisplayed() ||
                   driver.findElement(By.id("ctl00_phBody_SignUp_revCustMobile")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false; // Avoids crashes if no error message is found
        }
    }

    
    
    By userProfile = By.id("ctl00_lblUser");
    By logoutBtn = By.id("ctl00_lnkbtnLogout");

    public void logout() {
        // Create Actions instance for hover operation
        Actions act = new Actions(driver);

        // Locate elements for hovering & clicking logout
        WebElement profileElement = wait.until(ExpectedConditions.presenceOfElementLocated(userProfile));
        WebElement logoutElement = wait.until(ExpectedConditions.presenceOfElementLocated(logoutBtn));

        // Perform hover and click logout
        act.moveToElement(profileElement).moveToElement(logoutElement).click().perform();

        System.out.println("User logged out successfully.");
    }


    public boolean isRegistrationSuccessful() {
        try {
            return driver.findElement(successIndicator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getDisplayedErrorMessage() {
        try {
            WebElement errorElement = driver.findElement(errorMessageLabel);
            return errorElement.getText().trim();
        } catch (Exception e) {
            return "Unknown error or no message displayed";
        }
    }
}