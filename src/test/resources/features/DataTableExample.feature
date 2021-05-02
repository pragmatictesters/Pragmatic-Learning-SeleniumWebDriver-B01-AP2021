Feature: Data tables


  Scenario: Test with data table with a single row
    Given I have accessed the order page
    When I give product details in a single row
      | Product 1 | 12.5 | 3 |
    Then Click save button


  Scenario: Test with data table with multiple rows
    Given I have accessed the order page
    When I give product details in multiple row
      | Product 1 | 12.5 | 3 |
      | Product 2 | 15.5 | 2 |
      | Product 3 | 20.0 | 1 |
    Then Click save button

  Scenario: Test with data table with column names
    Given I have accessed the order page
    When I give product details in a table with column names
      | ProductName | Price | Quantity |
      | Product 1   | 12.5  | 3        |
      | Product 2   | 15.5  | 2        |
      | Product 3   | 20.0  | 1        |
    Then Click save button


  Scenario: Test with data table with keys in first column
    Given I have accessed the order page
    When I give product details in a table with keys in first column
      | ProductName | Product 1 |
      | Price       | 12.5      |
      | Quantity    | 3         |
    Then Click save button





