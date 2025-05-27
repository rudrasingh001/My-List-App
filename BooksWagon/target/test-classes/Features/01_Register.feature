@Signup
Feature: User Signup Functionality

  Scenario: Validate signup with data from Excel
    Given User is on the BooksWagon registration page
    When User fetches signup data from Excel and performs signup
    Then User verifies signup result