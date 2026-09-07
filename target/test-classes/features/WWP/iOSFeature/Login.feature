Feature: Login with Valid Credentials.

  @IOS
  Scenario Outline: Verify that user is able to login to the app
    #Given Read the testdata "<TestData>" from excel file
    When User launches the application on iOS "<deviceName>"

    Examples:
      | TestData |deviceName|
      |testdata1| iPhone 12_15.5|
