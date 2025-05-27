package booksWagon.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
 
public class DriverManager {
    private static WebDriver driver;
 
 
    private DriverManager() { } 
 
    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new EdgeDriver();
        }
        return driver;
    }
    
    public static void navigateToHome() {
        getDriver().get("https://www.bookswagon.com/");
    }
 
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}