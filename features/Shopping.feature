

Feature: Vegetables Cart

@Smoke @Regression
  Scenario Outline: Adding Vegetables to Cart
  Given User is on "shopping" page 
  When User selects the item and adds to cart
    |Item|Quantity| 
    |Cucumber |2|
    |Carrot |3|
    |Mango|2|
 And the user clicks on proceed to check out 
 Then user verifies the purchase and adds "<Discount>" and place order
|Cucumber|2|48|96|
|Carrot|3|56|168|
|Mango|2|75|150|
And user selects "<country>" with "<policy>" terms and condition proceed 
And user verfies "<message>"

Examples:
|country|Discount|policy|message|
|Australia|no|yes|yes|
|China|discount|no|no|