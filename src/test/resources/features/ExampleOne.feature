Feature:  Login to PTL HRM
  In this feature we will test the user login with valid credentials
  and invalid credentials.

  #Additional comments will go here

  Scenario: Login with valid user credential
    Given User has launched a browser
    And Use has accessed the login page
    When User type username "Admin"
    And User type password "Ptl@#321"
    And Click the login button
    Then User should be in the landing page
    And Welcome message "Welcome Admin" should be displayed

  Scenario: Login with invalid password
    Given User has launched a browser
    And Use has accessed the login page
    When User type username "Admin"
    And User type password "invalid"
    And Click the login button
    Then user should see an error message "Invalid credentials"


  Scenario: Login with blank user name
    Given User has launched a browser
    And Use has accessed the login page
    When User type username ""
    And User type password "invalid"
    And Click the login button
    Then User should see error message "Username cannot be empty"

  Scenario Outline: Login with invalid input data
    Given User has launched a browser
    And Use has accessed the login page
    When User type username "<username>"
    And User type password "<password>"
    And Click the login button
    Then User should see error message "<errormessage>"
    Examples:
      | username | password | errormessage             |
      | Admin    | Invalide | Invalid credentials      |
      |          | Ptl@#321 | Username cannot be empty |
      |          |          | Username cannot be empty |
      | Admin    |          | Password cannot be empty |


  Scenario Template: Login with invalid input data
    Given User has launched a browser
    And Use has accessed the login page
    When User type username "<username>"
    And User type password "<password>"
    And Click the login button
    Then User should see error message "<errormessage>"
    Scenarios:
      | username | password | errormessage             |
      | Admin    | Invalide | Invalid credentials      |
      |          | Ptl@#321 | Username cannot be empty |
      |          |          | Username cannot be empty |
      | Admin    |          | Password cannot be empty |


  Scenario: Test with different data types as input
    Given I have accessed the order page
    When I type item name "Product 1"
    And Type price 12.5
    And Quantity as 3
    And Description as
      """
        This is a long description
        line 2
        line 3
       """
    And Click save button
    Then total should be 37.5












