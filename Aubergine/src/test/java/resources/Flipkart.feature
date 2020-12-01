#Author: Ashvin.Piprotar
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.

@functional
Feature: Search product from flipcart
  Search flipcart website from google, search product from flipcart and add into cart.

  @functional
  Scenario Outline: Open Google.com
    Given that given <browser> has been opened
    When I launch <url>
    Then google search page should display
		
		Examples: 
      | browser | url |
      | Chrome | https://www.google.com |
    
  @functional
  Scenario: Search for "Flipkart"
    Given google search page is opened
    When I enter "Flipkart" in search text box
    Then I verify the Flipkart keyword is searched by google

  @functional
  Scenario: Print all the search options displayed in the console from google’s search field, while we write “Flipkart” in that.
    Given Flipkart keyword is searched from google
    When I get all the searched results
    Then I should be able to print search results
    
  @functional
  Scenario: Press enter to give out the search results and open the link for the “flipkart.com” website available.
    Given Flipkart keyword is searched from google
    When I open flipkart link from search result
    Then I should be able to see flipkart page is opened
    
  @functional
  Scenario: Close the login popup on the website (if available)
    Given I am on Flipkart page
    When I close login window if opened
    Then I should be able to see flipkart page is opened
    
  @functional
  Scenario: Click on “TV & Appliances” dropdown button and Navigate to “AirConditioners > Window AC’s” page
    Given I am on Flipkart page
    When I select window AC
    Then I should be able to see winodw AC page is opened
         
  @functional
  Scenario: Click on the “Add To Compare” checkbox of the 2nd, 3rd and 6th products displayed.
    Given I am on Window AC page
    When I select compare button of 2nd 3rd and 6th product
    Then I should be able to see 2nd 3rd and 6th product are selected
    
  @functional
  Scenario: then Click on the COMPARE button
    Given I selected products for compare
    When I click on compare button
    Then I moved to copare product page
     
  @functional
  Scenario: Print Name and price of all three products in the console
    Given I am in compare product page
    When I get Product name and price of all compared product
    Then I print Product name and price of all compared product
        
	@functional
  Scenario: Add all the 3 products into the cart, one by one
    Given I am in compare product page
    When I add all three product on cart
    Then I should be able to see products are added in Cart    
   
  @functional
  Scenario Outline: Go to the cart and add your area Pincode and check the availability of the product delivery there
    Given I am in My Cart page
    When I enter my <AreaPinCode> Code number
    Then Verify available products for entered PIN Code 
    
  	Examples: 
	    | AreaPinCode |
	    | 382481 | 

  @functional
  Scenario: Print the message getting displayed for the availability/delivery of the product in the console
    Given I am in My Cart page
    When I get the available product details
    Then I print available product details on console
    
  @functional
  Scenario Outline: Click the ‘Deliver to’ input box, available to the top of the page, and add another pin code and check the availability of the product delivery there
    Given I am in My Cart page
    When I change my <AreaPinCode> Code number
    Then Verify available products for entered PIN Code
    
  	Examples: 
	    | AreaPinCode |
	    | 380054 | 

  @functional
  Scenario: Print the message getting displayed for the availability/delivery of the product in the console for the changed Pincode
    Given I am in My Cart page
    When I get the available product details
    Then I print available product details on console

  @functional
  Scenario: Close the browser
    Given I am in My Cart page
    When I close browser

