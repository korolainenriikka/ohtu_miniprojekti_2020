Feature: as a user I want to be able to delete an entry

    Scenario: Entry in application is deleted
        Given entry with title "Entry!" is added
        And action "3" is chosen
        When title "Entry!" is entered
        Then system will respond with "Lukuvinkki poistettu onnistuneesti"

      Scenario: Deletion with empty input displays error message
        Given action "3" is chosen
        When title "" is entered
        Then system will respond with "Lukuvinkin poistaminen epäonnistui"

     Scenario: Deletion with entry not in application displays error message
        Given action "3" is chosen
        When title "A really cool entry" is entered
        Then system will respond with "Lukuvinkin poistaminen epäonnistui"
