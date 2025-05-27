package booksWagon.Pages;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import booksWagon.Utils.DriverManager;
import booksWagon.Utils.ExcelUtils;



public class SearchPage {
	WebDriver driver;
	

	public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

	
	

	 public void enterAndSearch(String word) {
		 WebElement searchBar = driver.findElement(By.xpath("//input[@id='inputbar']"));
		 searchBar.clear();
		 searchBar.sendKeys(word);
		 WebElement searchButton = driver.findElement(By.xpath("//input[@id='btnTopSearch']"));
		 searchButton.click();
	 }
	 
	 public boolean validateSearch(String word) {

		 System.out.println("//div/div/a[contains(text(),'" + word + "')]");
		 
		 if(!word.equals("")) {
			 List<WebElement> elements = driver.findElements(By.xpath("//div/div/a[contains(text(),'" + word + "')]"));
			 List<String> list = new ArrayList<>();
			 for (WebElement ele : elements) {
				 list.add(ele.getText());
			 }
			 
			 for (String s : list) {
				 if (!s.toLowerCase().contains(word.toLowerCase())) {
				        return false;
				 }
			 }
		 }
		 else {
			 String expectedURL = "https://www.bookswagon.com/";
			 if(expectedURL.equals(driver.getCurrentUrl())) return true;
			 else return false;
		 }

		 return true;
	 }
	 
	 
	 public void navigateBack() {
		 driver.navigate().to("https://www.bookswagon.com");
	 }
	
	 
//	 not to use
	 public void enter_and_search_keyword(String keyword) {
		 
		 	By searchBar = By.xpath("//input[@id='inputbar']");
		 	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement bar = wait.until(ExpectedConditions.elementToBeClickable(searchBar));
	        bar.clear(); 
	        
	        if (keyword != null) {
	            bar.sendKeys(keyword);
	        }
	        
	        By searchButton = By.xpath("//input[@id='btnTopSearch']");
	        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement button = wait1.until(ExpectedConditions.elementToBeClickable(searchButton));
	        button.click();
	        
	 }
	 
	 
	 public void populateExcel(String file, List<String> al, int row) throws IOException {
		
		 for (int i = 1; i < row; i++) {
			 ExcelUtils.setCellData(file, "Data1", i, 2, al.get(i-1));
		 }
	 }
	  
	 
	 public void searchTitle() throws InterruptedException {
		 
		 Thread.sleep(10);
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[2]/a"))).click();
		 Thread.sleep(100);
	 }
	 
	 public boolean assertTitle() {
		 
		 List<WebElement> title = driver.findElements(By.xpath("//div[@class='title']")); 
		 List<String> list = new ArrayList<>();
		 
		 for (WebElement bookTitle : title) {
			 list.add(bookTitle.getText());
		 }
		 
		 for (int i = 0; i < list.size(); i++) {
			 System.out.println(list.get(i));
		 }
		 
		 for (int i = 1; i < list.size(); i++) {
			 if(!list.get(i).contains("Selenium") || list.get(i).contains("selenium")) {
				 return false;
			 }
		 }
		 
		 return true;	 
	 }
	
	 
	 public void searchPrice() throws InterruptedException {
		Thread.sleep(10);
		WebElement element = driver.findElement(By.xpath("//*[@id='site-wrapper']/div[1]/div[1]/div[2]/div[1]/ul[2]/li[2]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	 }
	 
	 
	 public boolean assertPrice() {
		 List<WebElement> price = driver.findElements(By.xpath("//div[@class='price']/div[@class='sell']")); 
		 List<String> list = new ArrayList<>();
		 String priceSymbol = "";
		 String cleanPrice = "";
		 
		 for (WebElement bookPrice : price) {
			 priceSymbol = bookPrice.getText();
			 cleanPrice = priceSymbol.replace("₹", "");
			 list.add(cleanPrice);
		 }
		 
		 for (int i = 0; i < list.size(); i++) {
			 System.out.println(list.get(i));
		 }
		 
		 int num = 0;
		 for (int i = 0; i < list.size(); i++) {
			 num = Integer.parseInt(list.get(i));
			 if(!(num >= 100 && num <= 500)) {
				 return false;
			 }
		 }
		 
		 return true;
	 }
	 
	 
//	todo - giving error
	 public void searchDiscount() throws InterruptedException {		 
		 Thread.sleep(10);
		 driver.findElement(By.xpath("//*[@id='site-wrapper']/div[1]/div[1]/div[2]/div[1]/ul[3]/li[2]/a")).click();
	 }
	 
	 public void searchAvailability() throws InterruptedException {
		 Thread.sleep(10);
		 driver.findElement(By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[1]/div[2]/div[1]/ul[4]/li[3]/a")).click();
	 }
	 
	 
	 public Boolean assertAvailability() {
		 List<WebElement> avail = driver.findElements(By.xpath("//div[@class='out-of-stock']"));
		 List<String> list = new ArrayList<>();
		 
		 for (int i = 0; i < avail.size(); i++) {
			 list.add(avail.get(i).getText());
		 }
		 
		 for (int i = 0; i < list.size(); i++) {
			 System.out.println(list.get(i));
		 }
		 
		 for (int i = 0; i < list.size(); i++) {
			 if (!list.get(i).equalsIgnoreCase("out of stock")) {
				 return false;
			 }
		 }
		 
		 return true;
	 }
	 
	 
	 public void searchShippingTime() throws InterruptedException {
		 driver.navigate().refresh();
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		 for (int i = 0; i < 3; i++) { // Try 3 times
		     try {
		         WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Within 1 Week ')]")));
		         element.click();
		         break; // Exit loop on success
		     } catch (StaleElementReferenceException e) {
		         System.out.println("Retrying due to stale element...");
		     }
		 }
	 }
	 
	 
//	 debug
	 public void assertShippingTime() {
		 List<WebElement> ship = driver.findElements(By.xpath("//div[contains(text(), 'Ships within')]"));
		 List<String> list = new ArrayList<>();
		 
		 for (WebElement ele : ship) {
			 list.add(ele.getText());
		 }
		 
		 for (int i = 0; i < list.size(); i++) {
			 System.out.println(list.get(i));
		 }
	 }
	 
	 
//	 todo - giving error
	 public void searchSource() throws InterruptedException {
		 Thread.sleep(10);
//		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		  WebElement source = wait.until(ExpectedConditions.elementToBeClickable(
//				  By.xpath("//*[@id='site-wrapper']/div[1]/div[1]/div[2]/div[1]/ul[6]/li[2]/a")
//		     ));
		  driver.findElement(By.xpath("//*[@id='site-wrapper']/div[1]/div[1]/div[2]/div[1]/ul[6]/li[2]/a")).click();
	 }
	 
	 
	 public void searchBinding() throws InterruptedException {
		 Thread.sleep(10);
		  WebElement element = driver.findElement(By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[1]/div[2]/div[1]/ul[7]/li[2]/a"));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	 }
	 
	 
	 public boolean assertBinding() {
		 List<WebElement> bind = driver.findElements(By.xpath("//div[text()='Paperback']"));
		 List<String> list = new ArrayList<>();
		 
		 for (WebElement ele : bind) {
			 list.add(ele.getText());
		 }
		 
		 for (int i = 0; i < list.size(); i++) {
			 System.out.println(list.get(i));
		 }
		 
		 for (int i = 0; i < list.size(); i++) {
			 if (!list.get(i).equalsIgnoreCase("paperback")) {
				 return false;
			 }
		 }
		 return true;
	 }
	 
	 
	 public void searchLanguage() throws InterruptedException {
		 Thread.sleep(10);
		 WebElement element = driver.findElement(By.xpath("//*[@id=\"site-wrapper\"]/div[1]/div[1]/div[2]/div[1]/ul[8]/li[2]/a"));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	 }
	 
	 
	 public boolean assertLangauge() {
		 List<WebElement> lang = driver.findElements(By.xpath("//div[text()='English']"));
		 List<String> list = new ArrayList<>();
		 
		 for (WebElement ele : lang) {
			 list.add(ele.getText());
		 }
		 
		 for (int i = 0; i < list.size(); i++) {
			 System.out.println(list.get(i));
		 }
		 
		 for (int i = 0; i < list.size(); i++) {
			 if (!list.get(i).equalsIgnoreCase("English")) {
				 return false;
			 }
		 }
		 
		 return true;
	 }
	 
	 
	 public void searchPublicationYear() throws InterruptedException {
		 Thread.sleep(10);
		  WebElement element = driver.findElement(By.xpath("//*[@id='site-wrapper']/div[1]/div[1]/div[2]/div[1]/ul[9]/li[2]/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);	  
		
	 }
	 
	 
	 public boolean assertPublicationYear() {
		 List<WebElement> year = driver.findElements(By.xpath("//div[contains(text(),'2025')]"));
		 List<String> list = new ArrayList<>();
		 
		 for (WebElement ele : year) {
			 list.add(ele.getText());
			 System.out.println(ele.getText());
		 }
		 
		 for (int i = 0; i < list.size(); i++) {
			 System.out.println(list.get(i));
		 }
		 
		 int num = 0;
		 for (int i = 0; i < list.size(); i++) {
			 num = Integer.parseInt(list.get(i));
			 if (num != 2023) {
				 return false;
			 }
		 }
		 return true;
	 }
	 

}