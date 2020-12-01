Feature: as a user I want to be able to delete entries.

    Scenario: An entry that is in the database is deleted
        Given action "3" is chosen
        When existing entry "1" is selected
        Then system will respond with "Lukuvinkin poistaminen onnistui"

    Scenario: Deleting an entry not in the database ends in an error
        Given action "3" is chosen
        When non-existent entry "1" is selected
        Then system will respond with "Virhe: Vääränlainen syöte"