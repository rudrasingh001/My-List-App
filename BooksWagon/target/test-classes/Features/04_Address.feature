@Address
Feature: Checking Address Functionality 

  @AddingNewAddress
  Scenario: Adding new address in Address Section
  Given user is on homepage and navigating to my address section
  When user is clicking add option and enters the details in fields available in add address section and clicks update
  Then user is able to add address in my address section
    

  @EditingOldAddress
  Scenario: Editing old address in Address Section
   Given user is on homepage and navigating to my address section
   When user patches the data to old address and click update 
   Then user is able to update the exisiting address

    
