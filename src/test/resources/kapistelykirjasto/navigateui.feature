Feature: as a user I want to be able to navigate the user interface

    Scenario: Show possible actions
        When action "0" is chosen
        Then system will respond with "- 0: tulosta valikko"

    Scenario: Show possible actions
        When action "1" is chosen
        Then system will respond with "[1]: lisää vain otsikko"

    Scenario: Show possible actions
        When action "2" is chosen
        Then system will respond with "Kirjavinkit: "

    Scenario: Show possible actions
        When action "3" is chosen
        Then system will respond with "Syötä poistettavan lukuvinkin otsikko:"

    Scenario: Stop application
        When action "X" is chosen
        Then system will respond with "suljetaan"

    Scenario: Invalid input
        When action "heimoi" is chosen
        Then system will respond with "epäkelpo toiminto"
