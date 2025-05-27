package booksWagon.StepDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import booksWagon.Pages.AddressPage;
import booksWagon.Utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddressSteps {

    WebDriver driver = DriverManager.getDriver(); 
    static AddressPage addressPage;
    String excelPath = "C:\\Users\\shaurya.singh2\\eclipse-workspace\\BooksWagon\\src\\test\\resources\\TestingData\\TestData.xlsx";
    String sheetNewAddress = "AddingAddress";
    String sheetEditAddress = "EditingAddress";

    @Given("user is on homepage and navigating to my address section")
    public void user_is_on_homepage_and_navigating_to_my_address_section() throws InterruptedException {
        if (addressPage == null) {
            addressPage = new AddressPage(driver);
        }
        addressPage.navigateToMyAddressSection();
    }

    @When("user is clicking add option and enters the details in fields available in add address section and clicks update")
    public void user_is_clicking_add_option_and_enters_the_details_in_fields_available_in_add_address_section_and_clicks_update() throws IOException {
        addressPage.fillAddressForm(excelPath, sheetNewAddress);
    }

    @Then("user is able to add address in my address section")
    public void user_is_able_to_add_address() {
        List<String> expected = new ArrayList<>();
        expected.add("Mehul");

        List<String> actual = addressPage.savedAddress();
        driver.navigate().back();
        Assert.assertEquals(actual, expected);
    }


    @When("user patches the data to old address and click update")
    public void user_updates_existing_address() throws IOException {
        addressPage.fillAddressForm2(excelPath, sheetEditAddress);
    }

    @Then("user is able to update the exisiting address")
    public void user_is_able_to_update_address() {
        List<String> expected = new ArrayList<>();
        expected.add("Shaurya");
        expected.add("Dilip");

        List<String> actual = addressPage.savedAddress();
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }
}
