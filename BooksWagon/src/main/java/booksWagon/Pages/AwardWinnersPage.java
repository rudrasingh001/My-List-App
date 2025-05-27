package booksWagon.Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AwardWinnersPage {

    By opt = By.id("ddlSort");
    By price = By.xpath("//span[@class='actualprice themecolor  font-weight-bold']");
    By dis = By.xpath("//div[@class='offer position-absolute']");

    WebDriver driver;
    WebDriverWait wait;
    Select slct;

    public AwardWinnersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    
    public void navigatingToAwardWinners() {
       Actions act = new Actions(driver);
       WebElement ele1 = driver.findElement(By.id("ctl00_lblUser"));
       WebElement ele2 = driver.findElement(By.xpath("//a[contains(text(),'Your Account')]"));
       act.moveToElement(ele1).moveToElement(ele2).click().perform();
    	
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Award Winners')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_phBody_lblHeading")));
    }

    public void navToAWinners() {
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Award Winners')]"))).click();
    }
    
    public void sortingLowToHigh() {
        WebElement ele = driver.findElement(opt);
        slct = new Select(ele);
        slct.selectByVisibleText("Price - Low to High");
        wait.until(ExpectedConditions.visibilityOfElementLocated(price));
    }

   
    public void sortingHighToLow() {
        WebElement ele = driver.findElement(opt);
        slct = new Select(ele);
        slct.selectByVisibleText("Price - High to Low");
        wait.until(ExpectedConditions.visibilityOfElementLocated(price));
    }

    
    public void sortingByDiscount() {
        WebElement ele = driver.findElement(opt);
        slct = new Select(ele);
        slct.selectByVisibleText("Discount");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dis));
    }

    
    public List<Integer> checkingSortingByPrice() throws InterruptedException {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(price));;
        List<WebElement> webList = driver.findElements(price);
        List<Integer> list = new ArrayList<>();
        for (WebElement ele : webList) {
            String str = ele.getText();
            String cleanedStr = str.replaceAll("[₹,]", "");
            list.add(Integer.parseInt(cleanedStr));
        }
        return list;
    }

    
    public List<Integer> checkingSortingByDiscount() {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(dis));
        List<WebElement> webList = driver.findElements(dis);
        if (!webList.isEmpty()) {
            webList.remove(webList.size() - 1);
        }
        List<Integer> list = new ArrayList<>();
        for (WebElement ele : webList) {
            String str = ele.getText();
            String cleanedStr = str.replaceAll("%", "");
            list.add(Integer.parseInt(cleanedStr));
        }
        return list;
    }

    
    public boolean isSortedAscending(List<Integer> list) {
    	System.out.println(list.toString());
    	List<Integer> temp = list;
    	Collections.sort(temp);
    	return list.equals(temp);
    }

    
    public boolean isSortedDescending(List<Integer> list) {
        List <Integer> temp = list;
        Collections.sort(temp, Collections.reverseOrder());
        return list.equals(temp);
    }
}
