@Sorting
Feature: Navigating to Award Winners Section to check sort by functionality

  @PriceLowToHigh
  Scenario: Checking sort by functionality through sorting by Price Low to High
    Given user is on homepage and navigating to Award Winners section
    When user is selecting the option sort by Low to High of Price
    Then Books are sorted in Low to High of Price

  @PriceHighToLow
  Scenario: Checking sort by functionality through sorting by Price High To Low
    Given user is navigating to Award Winners section
    When user is selecting the option sort by High To Low of Price
    Then Books are sorted in High To Low of Price

  @Discount
  Scenario: Checking sort by functionality through sorting by Discount
    Given user is navigating to Award Winners section
    When user is selecting the option sort by Discount
    Then Books are sorted in High To Low of discount price
