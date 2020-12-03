Feature: as an user I want to see a list of entries

    Scenario: Entries are not listed when there are no entries added
        When action "2" is chosen
        Then system will respond with "Ei lisättyjä lukuvinkkejä"

    Scenario: List of entries contains added book
        Given book with title "kirja" is added
        When action "2" is chosen
        Then system will respond with "kirja"

    Scenario: List of entries contains added video
        Given video with title "video" is added
        When action "2" is chosen
        Then system will respond with "video"

    Scenario: List of read entries contains all read books
        When book with title "otsikko", author "aaa", ISBN "123" and comment "" is added
        And existing book with id "1" is marked as read
        When action "2" is chosen
        And type "1" is selected
        Then system will respond with "otsikko"
        And read book with title "otsikko", author "aaa", ISBN "123" and comment "" is listed as read

    Scenario: List of read entries contains all read videos
        When video with title "otsikko4", url "url", duration "123" and comment "" is added
        And existing video with id "1" is marked as read
        When action "2" is chosen
        And type "1" is selected
        Then system will respond with "otsikko4"
        And read video with title "otsikko4", url "url", duration "123" and comment "" is listed as read

    Scenario: List of not read entries contains all not read books
       When book with title "otsikko3", author "aaa", ISBN "123" and comment "" is added
       When action "2" is chosen
       And type "2" is selected
       Then not read book with title "otsikko3", author "aaa", ISBN "123" and comment "" is not listed as read

    Scenario: List of not read entries contains all not read videos
       When video with title "otsikko6", url "url", duration "123" and comment "" is added
       When action "2" is chosen
       And type "2" is selected
       Then not read video with title "otsikko6", url "url", duration "123" and comment "" is not listed as read