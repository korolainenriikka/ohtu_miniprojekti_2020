Feature: as a user I want to be able to mark entries as read.

  Scenario: A book that is in the database can be marked as read
    Given action "5" is chosen
    And book with title "Learning R", author "Richard Cotton", ISBN "3859" and comment "" is added
    When existing entry "1" is selected
    Then system will respond with "Kirja merkitty luetuksi"

  Scenario: An invalid index input will not allow entry to be marked as read
    Given action "5" is chosen
    And book with title "Python Crash Course", author "Eric Matthes", ISBN "675" and comment "" is added
    When existing entry "99999" is selected
    Then system will respond with "Virheellinen syöte"