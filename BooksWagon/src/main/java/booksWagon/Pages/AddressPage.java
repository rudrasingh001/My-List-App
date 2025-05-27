package booksWagon.Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import booksWagon.Utils.ExcelUtils;

public class AddressPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By myAddressSection = By.xpath("//a[@href='myaddress.aspx']");
    By addNewAddressBtn = By.xpath("//a[@href='addaddress.aspx']");
   

    // Address form fields
    By fullName  = By.id("ctl00_phBody_ShippingAddress_txtRecipientName");
    By companyName = By.id("ctl00_phBody_ShippingAddress_txtCompanyName");
    By streetAddress = By.id("ctl00_phBody_ShippingAddress_txtAddress");
    By landmark  = By.id("ctl00_phBody_ShippingAddress_txtLandmark");
    By countryAdd = By.id("ctl00_phBody_ShippingAddress_ddlCountry");
    By stateAdd = By.id("ctl00_phBody_ShippingAddress_ddlState");
    By cityAdd  = By.id("ctl00_phBody_ShippingAddress_ddlCities");
    By pinCode = By.id("ctl00_phBody_ShippingAddress_txtPincode");
    By mobileAdd = By.id("ctl00_phBody_ShippingAddress_txtMobile");
    By phoneAdd = By.id("ctl00_phBody_ShippingAddress_txtPhone");
    By defaultAdd = By.id("ctl00_phBody_ShippingAddress_chkDefaultAdd");
    By updateBtn = By.id("ctl00_phBody_ShippingAddress_imgSubmit");

    
    

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToMyAddressSection() {
        driver.findElement(myAddressSection).click();
    }
  

    public void clickAddNewAddress() {
    	driver.findElement(addNewAddressBtn).click();
    }
    
    public void fillAddressForm(String excelPath, String sheetName) throws IOException {
    	
    	
    	int rowCount = ExcelUtils.getRowCount(excelPath, sheetName);

        for (int i = 1; i < rowCount; i++) {
        	try {
        		clickAddNewAddress();
	        	
	        	String name = ExcelUtils.getCellData(excelPath, sheetName, i, 0);
	            String company = ExcelUtils.getCellData(excelPath, sheetName, i, 1);
	            String street = ExcelUtils.getCellData(excelPath, sheetName, i, 2);
	            String landmark = ExcelUtils.getCellData(excelPath, sheetName, i, 3);
	            String country = ExcelUtils.getCellData(excelPath, sheetName, i, 4);
	            String state = ExcelUtils.getCellData(excelPath, sheetName, i, 5);
	            String city = ExcelUtils.getCellData(excelPath, sheetName, i, 6);
	            String pin = ExcelUtils.getCellData(excelPath, sheetName, i, 7);
	            String mobile = ExcelUtils.getCellData(excelPath, sheetName, i, 8);
	            String phone = ExcelUtils.getCellData(excelPath, sheetName, i, 9);

	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtRecipientName")).clear();
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtRecipientName")).sendKeys(name);
	            
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtCompanyName")).clear();
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtCompanyName")).sendKeys(company);
	            
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtAddress")).clear();
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtAddress")).sendKeys(street);
	            
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtLandmark")).clear();
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtLandmark")).sendKeys(landmark);
	            
	            wait.until(ExpectedConditions.elementToBeClickable(countryAdd));
	            WebElement countryName = driver.findElement(countryAdd);
	            Select slct1 = new Select(countryName);
	            slct1.selectByVisibleText(country);
	            
	            wait.until(ExpectedConditions.elementToBeClickable(stateAdd));
	            WebElement stateName = driver.findElement(stateAdd);
	            Select slct2 = new Select(stateName);
	            slct2.selectByVisibleText(state);
	            
	            wait.until(ExpectedConditions.elementToBeClickable(cityAdd));
	            WebElement cityName = driver.findElement(cityAdd);
	            Select slct3 = new Select(cityName);
	            slct3.selectByVisibleText(city);
	            
	            driver.findElement(pinCode).clear();
	            driver.findElement(pinCode).sendKeys(pin);

	            driver.findElement(mobileAdd).clear();
	            driver.findElement(mobileAdd).sendKeys(mobile);

	            driver.findElement(phoneAdd).clear();
	            driver.findElement(phoneAdd).sendKeys(phone);
	              
	            clickUpdate();
	            if (isAlertPresent()) {
	            	ExcelUtils.setCellData(excelPath, sheetName, i, 11, "Failed");
	                continue; 
	            } else {
	                ExcelUtils.setCellData(excelPath, sheetName, i, 11, "Passed");
	            } 
        	}catch(Exception e) {
        		System.out.println("Error Found");
	        }	
        }
        
    }

    
    public void clickUpdate() {
        driver.findElement(updateBtn).click();
    }
    
    
    public void clickEditAddress() {
    	List<WebElement> editBtns = driver.findElements(By.xpath("//a[@class='themecolor editaddressbtn']"));
    	WebElement editAddressBtn = editBtns.get(0);
    	editAddressBtn.click();
    }
    
    
    
    
public void fillAddressForm2(String excelPath, String sheetName) throws IOException {
    	
    	
    	int rowCount = ExcelUtils.getRowCount(excelPath, sheetName);

        for (int i = 1; i < rowCount; i++) {
        	try {
        		clickEditAddress();
	        	
	        	String name = ExcelUtils.getCellData(excelPath, sheetName, i, 0);
	            String company = ExcelUtils.getCellData(excelPath, sheetName, i, 1);
	            String street = ExcelUtils.getCellData(excelPath, sheetName, i, 2);
	            String landmark = ExcelUtils.getCellData(excelPath, sheetName, i, 3);
	            String country = ExcelUtils.getCellData(excelPath, sheetName, i, 4);
	            String state = ExcelUtils.getCellData(excelPath, sheetName, i, 5);
	            String city = ExcelUtils.getCellData(excelPath, sheetName, i, 6);
	            String pin = ExcelUtils.getCellData(excelPath, sheetName, i, 7);
	            String mobile = ExcelUtils.getCellData(excelPath, sheetName, i, 8);
	            String phone = ExcelUtils.getCellData(excelPath, sheetName, i, 9);

	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtRecipientName")).clear();
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtRecipientName")).sendKeys(name);
	            
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtCompanyName")).clear();
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtCompanyName")).sendKeys(company);
	            
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtAddress")).clear();
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtAddress")).sendKeys(street);
	            
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtLandmark")).clear();
	            driver.findElement(By.id("ctl00_phBody_ShippingAddress_txtLandmark")).sendKeys(landmark);
	            
	            wait.until(ExpectedConditions.elementToBeClickable(countryAdd));
	            WebElement countryName = driver.findElement(countryAdd);
	            Select slct1 = new Select(countryName);
	            slct1.selectByVisibleText(country);
	            
	            wait.until(ExpectedConditions.elementToBeClickable(stateAdd));
	            WebElement stateName = driver.findElement(stateAdd);
	            Select slct2 = new Select(stateName);
	            slct2.selectByVisibleText(state);
	            
	            wait.until(ExpectedConditions.elementToBeClickable(cityAdd));
	            WebElement cityName = driver.findElement(cityAdd);
	            Select slct3 = new Select(cityName);
	            slct3.selectByVisibleText(city);
	            
	            driver.findElement(pinCode).clear();
	            driver.findElement(pinCode).sendKeys(pin);

	            driver.findElement(mobileAdd).clear();
	            driver.findElement(mobileAdd).sendKeys(mobile);

	            driver.findElement(phoneAdd).clear();
	            driver.findElement(phoneAdd).sendKeys(phone);
	              
	            clickUpdate();
	            if (isAlertPresent()) {
	            	ExcelUtils.setCellData(excelPath, sheetName, i, 11, "Failed");
	                continue; 
	            } else {
	                ExcelUtils.setCellData(excelPath, sheetName, i, 11, "Passed");
	            } 
        	}catch(Exception e) {
        		System.out.println("Error Found");
	        }	
        }
        
    }
    
    
    public boolean isAlertPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();  
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }
    
    public List<String> savedAddress(){
    	List<WebElement> list = driver.findElements(By.xpath("//div[@class='col-sm-12 addressboxheader']"));
        List<String> listString = new ArrayList<>();
        for(WebElement ele: list) {
        	listString.add(ele.getText());
        }
        return listString;
    }

    
}
