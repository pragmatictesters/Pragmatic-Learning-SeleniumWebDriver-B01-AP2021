Feature:  Add new employee feature


  Scenario: Add user with mandatory information
    Given  user has accessed the add new employee page
    When user type first name <"Firstname1">
    And user type last name  <"Lastname1">
    And click the save button
    Then Saved profile should be displayed
    And first name <"Firstname1"> should be displayed


