Feature: as a user I want to be able to add an entry

  Scenario: An entry with a new title is added
    Given application is launched
    When action "1" is chosen
    Then system will respond with "syötä otsikko:"
    When title "The Title" is entered
    Then system will respond with "lukuvinkki lisätty"

  Scenario: An entry with a pre-existing title isn't added
    Given application is launched
    And entry with title "Already taken" is added
    When action "1" is chosen
    Then system will respond with "syötä otsikko:"
    When title "Already taken" is entered
    Then system will respond with "lukuvinkkiä ei lisätty"
