package booksWagon.StepDefinition;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import booksWagon.Pages.AwardWinnersPage;
import booksWagon.Utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AwardWinnerSteps {
	WebDriver driver;
	AwardWinnersPage p;
	
//	private void ensureDriverInitialized() {
//	    if (driver == null) {
//	        driver = DriverManager.getDriver();
//	    }
//	}

    @Given("user is on homepage and navigating to Award Winners section")
    public void user_is_on_homepage_and_navigating_to_award_winners_section() {
       
        p = new AwardWinnersPage(driver);
        p.navigatingToAwardWinners();
    }

    @When("user is selecting the option sort by Low to High of Price")
    public void user_is_selecting_the_option_sort_by_low_to_high_of_price() {
        p.sortingLowToHigh();
    }

    @Then("Books are sorted in Low to High of Price")
    public void books_are_sorted_in_low_to_high_of_price() throws InterruptedException {
        List<Integer> actList = p.checkingSortingByPrice();
        System.out.println(actList);
        boolean check = p.isSortedAscending(actList);
        driver.get("https://www.bookswagon.com/myaccount.aspx");
        Assert.assertTrue(check, "Books are NOT sorted Low to High by price!");   
    }
    
    @Given("user is navigating to Award Winners section")
    public void user_is_navigating_to_Award_Winners_section() {
    	driver = DriverManager.getDriver();
        if (p == null) {
            p = new AwardWinnersPage(driver);
        }
        p.navToAWinners();
    }

    @When("user is selecting the option sort by High To Low of Price")
    public void user_is_selecting_the_option_sort_by_high_to_low_of_price() {
        p.sortingHighToLow();
    }

    @Then("Books are sorted in High To Low of Price")
    public void books_are_sorted_in_high_to_low_of_price() throws InterruptedException {
    	driver = DriverManager.getDriver();
        if (p == null) p = new AwardWinnersPage(driver);
        List<Integer> actList = p.checkingSortingByPrice();
        boolean check = p.isSortedDescending(actList);
        driver.get("https://www.bookswagon.com/myaccount.aspx");
        Assert.assertTrue(check, "Books are NOT sorted High to Low by price!");      
    }

    @When("user is selecting the option sort by Discount")
    public void user_is_selecting_the_option_sort_by_discount() {
        p.sortingByDiscount();
    }

    @Then("Books are sorted in High To Low of discount price")
    public void books_are_sorted_in_high_to_low_of_discount_price() {
    	driver = DriverManager.getDriver();
        if (p == null) p = new AwardWinnersPage(driver);
        
        List<Integer> actList = p.checkingSortingByDiscount();
        boolean check = p.isSortedDescending(actList);
        
        driver.get("https://www.bookswagon.com/myaccount.aspx");
        
        Assert.assertTrue(check, "Books are NOT sorted High to Low by discount!");
        
    }
}
