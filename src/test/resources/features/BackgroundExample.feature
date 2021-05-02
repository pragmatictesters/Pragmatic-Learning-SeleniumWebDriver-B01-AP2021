Feature: Login feature of PTL HRM

  Background:
    Given User has launched a browser
    And Use has accessed the login page


  Scenario: Login with valid user credential
    When User type username "Admin"
    And User type password "Ptl@#321"
    And Click the login button
    Then User should be in the landing page
    And Welcome message "Welcome Admin" should be displayed

  Scenario: Login with invalid password
    When User type username "Admin"
    And User type password "test23"
    And Click the login button
    Then User should see error message "Invalid credentials"