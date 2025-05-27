package booksWagon.StepDefinition;

//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//
//import booksWagon.Pages.LoginPage;
//import booksWagon.Utils.DriverManager;
import booksWagon.Utils.ExcelUtils;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class LoginStepDefinition {
//
//    WebDriver driver;
//    LoginPage loginPage;
//    String filePath = "C:\\Users\\rudra.singh\\Downloads\\MainProj-main (1)\\MainProj-main\\BooksWagon\\BooksWagon\\src\\test\\resources\\TestingData\\TestData.xlsx";
//    String sheetName = "LoginData";
//
//    @Given("User is on the BooksWagon homepage")
//    public void navigateToHomepage() {
//        System.out.println("Starting login test using ExcelUtils...");
//    }
//
//    @When("User fetches login data from Excel and performs login")
//    public void fetchDataAndLogin() throws Exception {
//        driver = DriverManager.getDriver();  // reuse existing browser
//        loginPage = new LoginPage(driver);
//        DriverManager.navigateToHome();
//
//        int rowCount = ExcelUtils.getRowCount(filePath, sheetName);
//
////        for (int i = 1; i < rowCount; i++) {
////            System.out.println("Running test iteration: " + i);
////
////            String mobile = ExcelUtils.getCellData(filePath, sheetName, i, 0);  // assuming "mobile" in column 0
////            String password = ExcelUtils.getCellData(filePath, sheetName, i, 1);  // "password" in column 1
////            String otp = ExcelUtils.getCellData(filePath, sheetName, i, 2);       // "otp" in column 2
////            String expectedResult = ExcelUtils.getCellData(filePath, sheetName, i, 3);  // "expectedResult" in column 3
////
////            loginPage.clickLoginHeader();
////            loginPage.enterMobileOrEmail(mobile);
////
////            if (password != null && !password.isEmpty()) {
////                loginPage.enterPassword(password);
////                loginPage.clickLoginButton();
////            } else {
////                loginPage.clickRequestOtp();
////                loginPage.waitForOtpInput();
////                loginPage.clickVerifyOtp();
////            }
////
////            boolean loginSuccess = loginPage.isLoginSuccessful();
////            String actualResult = loginSuccess ? "success" : "failure";
////            String testResult = actualResult.equalsIgnoreCase(expectedResult) ? "Pass" : "Fail";
////
////            // Write results
////            ExcelUtils.setCellData(filePath, sheetName, i, 4, actualResult); // "actualResult" in column 4
////            ExcelUtils.setCellData(filePath, sheetName, i, 5, testResult);   // "testResult" in column 5
////
////            if ("success".equalsIgnoreCase(expectedResult)) {
////                Assert.assertTrue(loginSuccess, "Expected login to succeed, but it failed");
////            } else {
////                Assert.assertFalse(loginSuccess, "Expected login to fail, but it succeeded");
////            }
////            
////            if (loginSuccess) {
////                loginPage.logout1();
////            }
////			
////            DriverManager.navigateToHome();
////        }
////    }
//
//        
//        
//        
//        for (int i = 1; i < rowCount; i++) {
//            System.out.println("Running test iteration: " + i);
//
//            String mobile = ExcelUtils.getCellData(filePath, sheetName, i, 0);
//            String password = ExcelUtils.getCellData(filePath, sheetName, i, 1);
//            String otp = ExcelUtils.getCellData(filePath, sheetName, i, 2);
//            String expectedResult = ExcelUtils.getCellData(filePath, sheetName, i, 3);
//            
//      
//
//
//            if (loginPage.isLoginSuccessful()) {
//                System.out.println("User already logged in. Skipping login step for row " + i);
//                continue;
//            }
//
//            loginPage.clickLoginHeader();
//            loginPage.enterMobileOrEmail(mobile);
//
//            if (password != null && !password.isEmpty()) {
//                loginPage.enterPassword(password);
//                loginPage.clickLoginButton();
//            } else {
//                loginPage.clickRequestOtp();
//                loginPage.waitForOtpInput();
//                loginPage.clickVerifyOtp();
//            }
//
//            boolean loginSuccess = loginPage.isLoginSuccessful();
//            String actualResult = loginSuccess ? "success" : "failure";
//            String testResult = actualResult.equalsIgnoreCase(expectedResult) ? "Pass" : "Fail";
//
//            ExcelUtils.setCellData(filePath, sheetName, i, 4, actualResult);
//            ExcelUtils.setCellData(filePath, sheetName, i, 5, testResult);
//
//            if ("success".equalsIgnoreCase(expectedResult)) {
//                Assert.assertTrue(loginSuccess, "Expected login to succeed, but it failed");
//            } else {
//                Assert.assertFalse(loginSuccess, "Expected login to fail, but it succeeded");
//
//                // Optional: Logout only if test was supposed to fail
//                if (loginSuccess) {
//                    loginPage.logout1();
//                }
//            }
//
//            DriverManager.navigateToHome();
//        }
//    }
//    @Then("User verifies login result")
//    public void userVerifiesLoginResult() {
//        System.out.println("Login scenarios executed successfully.");
//    }
//}





import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import booksWagon.Pages.LoginPage;
import booksWagon.Utils.DriverManager;

public class LoginStepDefinition {

    WebDriver driver;
    LoginPage loginPage;
    String filePath = "src/test/resources/testingData/TestData.xlsx";
    String sheetName = "LoginData";

    @Given("User is on the BooksWagon homepage")
    public void navigateToHomepage() {
        System.out.println("Starting login test using ExcelUtils...");
    }

    @When("User fetches login data from Excel and performs login")
    public void fetchDataAndLogin() throws Exception {
        driver = DriverManager.getDriver();  // reuse existing browser
        loginPage = new LoginPage(driver);
//        DriverManager.navigateToHome();

        int rowCount = ExcelUtils.getRowCount(filePath, sheetName);

        for (int i = 1; i < rowCount -1; i++) {
            System.out.println("Running test iteration: " + i);

            String mobile = ExcelUtils.getCellData(filePath, sheetName, i, 0);  // assuming "mobile" in column 0
            String password = ExcelUtils.getCellData(filePath, sheetName, i, 1);  // "password" in column 1
            String otp = ExcelUtils.getCellData(filePath, sheetName, i, 2);       // "otp" in column 2
            String expectedResult = ExcelUtils.getCellData(filePath, sheetName, i, 3);  // "expectedResult" in column 3

            loginPage.clickLoginHeader();
            loginPage.enterMobileOrEmail(mobile);

            if (password != null && !password.isEmpty()) {
                loginPage.enterPassword(password);
                loginPage.clickLoginButton();
            } else {
                loginPage.clickRequestOtp();
                loginPage.waitForOtpInput();
                loginPage.clickVerifyOtp();
            }

            boolean loginSuccess = loginPage.isLoginSuccessful();
            String actualResult = loginSuccess ? "success" : "failure";
            String testResult = actualResult.equalsIgnoreCase(expectedResult) ? "Pass" : "Fail";

            // Write results
            ExcelUtils.setCellData(filePath, sheetName, i, 4, actualResult); // "actualResult" in column 4
            ExcelUtils.setCellData(filePath, sheetName, i, 5, testResult);   // "testResult" in column 5

            if ("success".equalsIgnoreCase(expectedResult)) {
                Assert.assertTrue(loginSuccess, "Expected login to succeed, but it failed");
            } else {
                Assert.assertFalse(loginSuccess, "Expected login to fail, but it succeeded");
            }

//            if (loginSuccess) {
//                loginPage.logout1();
//            }

            DriverManager.navigateToHome();
        }
    }

    @Then("User verifies login result")
    public void userVerifiesLoginResult() {
        System.out.println("Login scenarios executed successfully.");
    }
}
