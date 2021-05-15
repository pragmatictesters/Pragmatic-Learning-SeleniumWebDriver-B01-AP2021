Feature: Test login to NCare


  Scenario: Login with unregistered email
    Given I have setup the browser driver "chrome"
    And I have launched the browser
    And I have navigated to the login page
    When I type email "test@gmail.com"
    And I type password "test123"
    And Click on the login button
    Then I should see error message "Your account is not active. Please register to your institutions account"


  Scenario Outline: Invalid login scenarios
    Given I have setup the browser driver "<browser>"
    And I have launched the browser
    And I have navigated to the login page
    When I type email "<email>"
    And I type password "<password>"
    And Click on the login button
    Then I should see error message "<expected_error>"
    Examples:
      | browser | email | password | expected_error |
    |   chrome      | test@gmail.com      |   test123       |   our account is not active. Please register to your institutions account             |