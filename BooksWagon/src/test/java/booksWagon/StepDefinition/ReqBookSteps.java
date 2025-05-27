package booksWagon.StepDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import booksWagon.Pages.ReqBookPage;
import booksWagon.Utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReqBookSteps {
	WebDriver driver;
	ReqBookPage req;

	
	@Given("User is on the home page")
	public void user_is_on_the_home_page() {
		driver = DriverManager.getDriver(); 
	    req = new ReqBookPage(driver);	
	}

	@When("User clicks on the request book section")
	public void user_clicks_on_the_request_book_section() {
		req.navigatingToReqBookSection();
	}

	@Then("User enters the details and clicks on submit")
	public void user_enters_the_details_and_clicks_on_submit() throws IOException, InterruptedException {
		List<Boolean> actRes = req.requestingTheBook();
		List<Boolean> expRes = new ArrayList<>();
		expRes.add(true);
		expRes.add(false);
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		Assert.assertEquals(actRes, expRes);
	}
}
