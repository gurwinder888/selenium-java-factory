Feature: Login with Valid Credentials.

  @Android
  Scenario Outline: Verify that user is able to login to the app
    #Given Read the testdata "<TestData>" from excel file
    When User launches the application on Android "<deviceName>"

    Examples:
      | TestData |deviceName|
      |testdata1|gbgyhhniu|
