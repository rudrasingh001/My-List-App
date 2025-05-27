@Login 
Feature: User Login Functionality

  Scenario: Validate login with data from Excel
    Given User is on the BooksWagon homepage
    When User fetches login data from Excel and performs login
    Then User verifies login result