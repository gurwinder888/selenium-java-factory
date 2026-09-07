Feature: Verify add to cart functionality

  @Web
  Scenario Outline: Verify that the user is able to add a product to the cart
    #Given Read the testdata "<TestData>" from excel file
    When User navigates to the url


    Examples:
      |TestData |
      |Testdata1|
