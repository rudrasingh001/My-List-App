package booksWagon.Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import booksWagon.Utils.ExcelUtils;

public class ReqBookPage {
	
	 WebDriver driver;
	 WebDriverWait wait;
	 By nav1 = By.xpath("//a[contains(text(),'Request a Book')]");
	 String dataPath = "C:\\Users\\shaurya.singh2\\eclipse-workspace\\BooksWagon\\src\\test\\resources\\TestingData\\TestData.xlsx";
	 String sheetName = "ReqBooks";
	
	public ReqBookPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	
	public void navigatingToReqBookSection() {
		driver.findElement(nav1).click();
	}
	
	public List<Boolean> requestingTheBook() throws IOException, InterruptedException {
		int row = ExcelUtils.getRowCount(dataPath, sheetName);
		String expSucReq = "We have saved your requested books successfully into our system. We will intimate you as soon as book available.";
		List<Boolean> list = new ArrayList<>();
		
		for(int i=1; i<row; i++) {
			String isbnData = ExcelUtils.getCellData(dataPath, sheetName, i, 0);
			String bookTitleData= ExcelUtils.getCellData(dataPath, sheetName, i, 1);
			String author= ExcelUtils.getCellData(dataPath, sheetName, i, 2);
			String Quantity= ExcelUtils.getCellData(dataPath, sheetName, i, 3);
			String Email_id= ExcelUtils.getCellData(dataPath, sheetName, i, 4);
			String PhoneNumber= ExcelUtils.getCellData(dataPath, sheetName, i, 5);
			System.out.println(isbnData);
			System.out.println(bookTitleData);
			System.out.println(author);
			System.out.println(Quantity);
			System.out.println(Email_id);
			System.out.println(PhoneNumber);
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtISBN")).clear();
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtISBN")).sendKeys(isbnData);
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtTitle")).clear();
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtTitle")).sendKeys(bookTitleData);
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtAuthor")).clear();
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtAuthor")).sendKeys(author);
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtQty")).clear();
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtQty")).sendKeys(Quantity);
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtEmailReq")).clear();
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtEmailReq")).sendKeys(Email_id);
			driver.findElement(By.id("ctl00_phBody_RequestBook_txtPhone")).sendKeys(PhoneNumber);
			Thread.sleep(2000);
			driver.findElement(By.id("ctl00_phBody_RequestBook_btnVeiry")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_phBody_RequestBook_imgbtnSave")));
			driver.findElement(By.id("ctl00_phBody_RequestBook_imgbtnSave")).click();
			By ele = By.id("ctl00_phBody_RequestBook_lblsuccessmsg");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			String actSucReq = driver.findElement(By.id("By.id('ctl00_phBody_RequestBook_lblsuccessmsg')")).getText();
			System.out.println(actSucReq);
			if(actSucReq.equals(expSucReq)) {
				list.add(true);
			}else {
				list.add(false);
			}	
		}
		return list;
	}
	

}
