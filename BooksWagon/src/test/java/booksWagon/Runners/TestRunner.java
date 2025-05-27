package booksWagon.Runners;

import org.testng.annotations.Listeners;

import booksWagon.Listeners.ExtentTestListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/resources/Features", glue= {"booksWagon.StepDefinition"}, tags=" @Login or @Signup  ")//or @Sorting or @Address or @ReqBook or  @searchFeature 

@Listeners(ExtentTestListener.class)

public class TestRunner extends AbstractTestNGCucumberTests{

}
