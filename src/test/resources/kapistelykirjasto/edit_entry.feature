Feature: as a user I want to be able to edit entries.

  Scenario: A book that is in the database can be edited
    Given action "4" is chosen
    And book with title "Clean Code", author "Robert Martin", ISBN "978-0132350884" and comment "" is added
    When existing entry "1" is selected
    And title "a Book", author "mr Author", ISBN "12345abc" and comment "a cool book!" is entered
    Then system will respond with "Lukuvinkki muokattu onnistuneesti"
    And book with title "a Book", author "mr Author", ISBN "12345abc" and comment "a cool book!" exists
    #And book with title "Clean Code", author "Robert Martin", ISBN "978-0132350884" and comment "" does not exist

  Scenario: An invalid index input will not allow editing
    Given action "4" is chosen
    And book with title "Clean Code", author "Robert Martin", ISBN "978-0132350884" and comment "" is added
    When existing entry "9999" is selected
    Then system will respond with "Virheellinen syöte"