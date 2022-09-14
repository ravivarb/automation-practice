Feature:Add to Cart Feature

  @web @test0
  Scenario: Able to find the max priced item in the dress section and add to cart
    Given I am on automation practice website
    And I navigate to dresses section
    When I choose the max priced item
    And I add the item into the shopping cart
    Then the item successfully added



