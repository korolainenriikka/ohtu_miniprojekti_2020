Feature: as an user I want to see a list of all entries

    Scenario: Books are listed when there are no books added
        When action "2" is chosen
        Then system will respond with "Ei lisättyjä kirjavinkkejä"

    Scenario: Videos are listed when there are no videos added
        When action "2" is chosen
        Then system will respond with "Ei lisättyjä videovinkkejä"

    Scenario: Added book is listed
        Given book with title "kirja" is added
        When action "2" is chosen
        Then system will respond with "kirja"

    Scenario: Added video is listed
        Given video with title "video" is added
        When action "2" is chosen
        Then system will respond with "video"

