@e2e
Feature: As a user, I should be able to verify the item list and add specific product to the cart

  Background:

    Given user is on the webpage

    @itemList
  Scenario: A user should be able to view 3 items displayed on the page in exact order and correct price

    When user see the results list
    Then user verifies the item
    And quit


      @cartTest
  Scenario: as a user i should be able to add item to cart and verify
    When user clicks on iPad 4 Mini
    And user gets item information
    And user adds to cart
    And cart is updated
    Then user verifies the stock
    And quit
