Feature: as an user I want to see a list of all entries

    Scenario: Entries are listed when there are no entries added
        When action "2" is chosen
        Then system will respond with "Ei lisättyjä lukuvinkkejä"

    Scenario: All entries added are listed
        Given entry with title "uusi vinkki!" is added
        And entry with title "toinen uusi vinkki!" is added
        When action "2" is chosen
        Then system will respond with "uusi vinkki!toinen uusi vinkki!"