Feature: as an user I want to see a list of all entries

    Scenario: Entries are listed when there are no books added
        When action "2" is chosen
        Then system will respond with "Ei lisättyjä kirjavinkkejä"
