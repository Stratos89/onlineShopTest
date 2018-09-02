Feature: AddToBasket
  As a user
  I want to add items to a shopping list
  So that I can keep track of what I need to buy on my next shop list

  Scenario: Adding items to shopping list
    Given I am an unregistered user on the retail store website
    When I search for "COMFORT FUTON - FOR DAYBEDS"
    And I select the first item
    And I add this item to the basket
    Then my basket contains one item
