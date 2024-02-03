

Feature: Vegetables Cart

@Smoke @Regression
  Scenario: Adding Vegetables to Cart
    Given User is on "shopping" page 
  When User selects the item and adds to cart
    |Item|Quantity| 
    |Cucumber |2|
     |Carrot |3|
     |Mango|2|
 And the user clicks on proceed to check out 
 Then user verifies the purchase and place order
# And user selects "Australia" and proceed 
#And user verfies "success"
