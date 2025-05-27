@ReqBook
Feature: To check request book functionality
	@REQ
  Scenario: Requesting the books
    Given User is on the home page
    When User clicks on the request book section
    Then User enters the details and clicks on submit
