Feature: Test login to NCare


  Scenario:
    Given I have setup the browser driver "chrome"
    And I have launched the browser
    And I have navigated to the login page
    When I type email "test@gmail.com"
    And I type password "test123"
    And Click on the login button
    Then I should see error message "Your account is not active. Please register to your institutions account"