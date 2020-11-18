Feature: as a user I want to be able to navigate the user interface

    Scenario: Show possible actions
        Given application is launched
        When action "0" is chosen
        Then the system will respond with "- 0: tulosta valikko"

    Scenario: Stop application
        Given application is launched
        When action "X" is chosen
        Then the system will respond with "suljetaan"

    Scenario: Invalid input
        Given application is launched
        When action "heimoi" is chosen
        Then the system will respond with "epäkelpo toiminto"
