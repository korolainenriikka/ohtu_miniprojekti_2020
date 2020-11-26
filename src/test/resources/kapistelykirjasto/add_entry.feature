Feature: as a user I want to be able to add an entry

  Scenario: An entry with a new title is added
    Given action "1" is chosen
    And type "1" is selected
    When title "The Title" is entered
    Then system will respond with "Lukuvinkki lisätty onnistuneesti"

  Scenario: A duplicate entry is not added
    Given entry with title "Already taken" is added
    And action "1" is chosen
    And type "1" is selected
    When title "Already taken" is entered
    Then system will respond with "Lukuvinkin lisääminen epäonnistui"

 Scenario: An empty entry is not added
    Given entry with title "" is added
    And action "1" is chosen
    And type "1" is selected
    When title "" is entered
    Then system will respond with "Lukuvinkin lisääminen epäonnistui"