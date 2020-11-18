Feature: As a user I want to be able to increase the counter value

  Scenario: Increment once
    Given Counter is initialized
    When it is incremented
    Then the value should be 1