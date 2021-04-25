Feature: Beer Cans

  Scenario: Counting Beer Cans
    Given I have <opening balance> beer cans
    And  I have drunk <processed> beer cans
    When I go to the fridge
    Then I should have <in stock> beer cans


  Scenario Outline: Counting Beer Cans
    Given I have <opening balance> beer cans
    And  I have drunk <processed> beer cans
    When I go to the fridge
    Then I should have <in stock> beer cans
    Examples:
      | opening balance | processed | in stock |
      | 5               | 1         | 4        |
      | 4               | 2         | 2        |
