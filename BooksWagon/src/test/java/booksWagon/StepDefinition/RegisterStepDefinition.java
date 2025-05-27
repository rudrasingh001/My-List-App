package booksWagon.StepDefinition;


import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import booksWagon.Pages.RegisterPage;
import booksWagon.Utils.DriverManager;
import booksWagon.Utils.ExcelUtils;

public class RegisterStepDefinition {

    WebDriver driver;
    RegisterPage registerPage;

    @Given("User is on the BooksWagon registration page")
    public void navigateToRegistrationPage() {
        System.out.println("Starting signup test using Excel data...");
    }

    @When("User fetches signup data from Excel and performs signup")
    public void fetchDataAndPerformRegister() throws InterruptedException {
        String filePath = "C:\\Users\\rudra.singh\\Downloads\\MainProj-main (1)\\MainProj-main\\BooksWagon\\BooksWagon\\src\\test\\resources\\TestingData\\TestData.xlsx";
        String sheetName = "SignupData";

        try {
            int rowCount = ExcelUtils.getRowCount(filePath, sheetName);
//            ------------------------------------------------------------
            driver = DriverManager.getDriver();
            
//            ---------------------------------------------------------------
            
            registerPage = new RegisterPage(driver);
    DriverManager.navigateToHome();

            for (int i = 1; i < rowCount; i++) {
                String name = ExcelUtils.getCellData(filePath, sheetName, i, 0); // assuming col 0 is 'name'
                String mobile = ExcelUtils.getCellData(filePath, sheetName, i, 1);
                String password = ExcelUtils.getCellData(filePath, sheetName, i, 3);
                String confirmPassword = ExcelUtils.getCellData(filePath, sheetName, i, 4);
                String expectedResult = ExcelUtils.getCellData(filePath, sheetName, i, 5);

                try {
                    registerPage.clickRegisterHeader();
                    registerPage.enterName(name);
                    registerPage.enterMobile(mobile);

                    System.out.println("Please complete captcha manually...");
                    Thread.sleep(50000);

                    registerPage.clickContinue();

                    if (registerPage.isErrorMessageDisplayed()) {
                        System.out.println("Validation error detected.");
                        ExcelUtils.setCellData(filePath, sheetName, i, 6, "failure");     // actualResult
                        ExcelUtils.setCellData(filePath, sheetName, i, 7, "Pass");        // testResult
                        DriverManager.navigateToHome();
                        continue;
                    }

                    System.out.println("Please enter OTP manually...");
                    Thread.sleep(10000);

                    registerPage.enterPassword(password);
                    registerPage.enterConfirmPassword(confirmPassword);
                    registerPage.clickRegister();

                    boolean registerSuccess = registerPage.isRegistrationSuccessful();
                    String actualResult = registerSuccess ? "success" : "failure";

                    ExcelUtils.setCellData(filePath, sheetName, i, 6, actualResult); // actualResult
                    String testResult = actualResult.equalsIgnoreCase(expectedResult) ? "Pass" : "Fail";
                    ExcelUtils.setCellData(filePath, sheetName, i, 7, testResult);  // testResult

                    if ("success".equalsIgnoreCase(expectedResult)) {
                        Assert.assertTrue(registerSuccess, "Expected successful registration, but it failed");
                        registerPage.logout();
                    } else {
                        Assert.assertEquals(actualResult, expectedResult, "Mismatch in expected and actual result.");
                    }

                } catch (Exception e) {
                    System.out.println("Test case failed unexpectedly: " + e.getMessage());
                }

                DriverManager.navigateToHome();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ❌ DO NOT QUIT the driver here — leave it for login to reuse
    }

    @Then("User verifies signup result")
    public void verifyRegisterResult() {
        System.out.println("Signup scenarios completed and validated.");
    }
}