Feature: as a user I want to be able to edit entries.

  Scenario: A book that is in the database can be edited
    Given action "4" is chosen
    And book with title "Hello uby!", author "Linda", ISBN "032-135522-" and comment "" is added
    When existing entry "1" is selected
    And title "Hello Ruby!", author "Linda Liukas", ISBN "032-135522-K" and comment "a cool book!" is entered
    Then system will respond with "Lukuvinkki muokattu onnistuneesti"
    And book with title "Hello Ruby!", author "Linda Liukas", ISBN "032-135522-K" and comment "a cool book!" exists
    And book with title "Hello uby!", author "Linda", ISBN "032-135522-" and comment "" does not exist

  Scenario: An invalid index input will not allow editing
    Given action "4" is chosen
    And book with title "Clean Code", author "Robert Martin", ISBN "978-0132350884" and comment "" is added
    When existing entry "9999" is selected
    Then system will respond with "Virheellinen syöte"
    
  Scenario: Trying to enter an invalid title while editing fails
    Given action "4" is chosen
    And book with title "Clean Code", author "Robert Martin", ISBN "978-013235088" and comment "" is added
    When existing entry "1" is selected
    And title "", author "Robert Martin Jr", ISBN "978-0132350884" and comment "" is entered
    Then system will respond with "Lukuvinkin muokkaaminen epäonnistui"