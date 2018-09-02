Feature: View basket
  As a user
  I want to see my entire shopping list
  So that I can decide to proceed to checkout

  Scenario: Viewing my basket
    Given I am an unregistered user on the retail store website
    And I search for "COMFORT FUTON - FOR DAYBEDS"
    And I select the first item
    And I add this item to the basket
    When I view my basket
    Then I see all the items I have added
    And the item is "Comfort Futon - for Daybeds"

