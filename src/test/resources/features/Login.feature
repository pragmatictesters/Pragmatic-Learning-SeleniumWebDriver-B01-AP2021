Feature: Login feature of PTL HRM

  @Smoke
  Scenario: Login with valid user credentials
    Given User has launched a browser
    And Use has accessed the login page
    When User type username "Admin"
    And User type password "Ptl@#321"
    And Click the login button
    Then User should be in the landing page
    And Welcome message "Welcome Admin" should be displayed
    But I should not see the "logout" link

  @Sanity
  Scenario: Login with valid user credentials (same)
    Given User has launched a browser
    * Use has accessed the login page
    When User type username "Admin"
    * User type password "Ptl@#321"
    * Click the login button
    Then User should be in the landing page
    * Welcome message "Welcome Admin" should be displayed

  @Reg
  Example: Login with valid user credentials (same 2)
    Given User has launched a browser
    * Use has accessed the login page
    When User type username "Admin"
    * User type password "Ptl@#321"
    * Click the login button
    Then User should be in the landing page
    * Welcome message "Welcome Admin" should be displayed

  Scenario: Login with invalid password
    Given User has launched a browser
    And Use has accessed the login page
    When User type username "Admin"
    And User type password "test23"
    And Click the login button
    Then User should see error message "Invalid credentials"


  Scenario: Login with invalid password with missing step
    Given User has launched a browser
    And  missing step 1
    And Use has accessed the login page
    When User type username "Admin"
    And User type password "test23"
    And Click the login button
    Then User should see error message "Invalid credentials"


  Scenario Outline: Test invalid user logins
    Given User has launched a browser
    And Use has accessed the login page
    When User type username "<username>"
    And User type password "<password>"
    And Click the login button
    Then User should see error message "<errorMessage>"
    @data-driven-tests
    Examples:
      | username | password | errorMessage             |
      | Admin    | test123  | Invalid credentials      |
      |          |          | Username cannot be empty |
      |          | Ptl@#321 | Username cannot be empty |
      | Admin    |          | Password cannot be empty |





