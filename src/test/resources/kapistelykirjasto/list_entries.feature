Feature: as an user I want to see a list of entries

    Scenario: Entries are not listed when there are no entries added
        When action "2" is chosen
        Then system will respond with "Ei lisättyjä lukuvinkkejä"

    Scenario: List of entries contains added book
        Given book with title "Elements of the Theory of Computation" is added
        When action "2" is chosen
        Then system will respond with "Elements of the Theory of Computation"

    Scenario: List of entries contains added video
        Given video with title "Slow sorting: Stooge sort and Bogo sort" is added
        When action "2" is chosen
        Then system will respond with "Slow sorting: Stooge sort and Bogo sort"

    Scenario: List of read entries contains all read books
        When book with title "Hello Ruby!", author "Linda Liukas", ISBN "032-135522-K" and comment "" is added
        And existing book with id "1" is marked as read
        When action "2" is chosen
        And type "1" is selected
        Then system will respond with "Hello Ruby!"

    Scenario: List of read entries contains all read videos
        When video with title "Visualization of Quick sort", url "https://www.youtube.com/watch?v=vxENKlcs2Tw", duration "2:56" and comment "" is added
        And existing video with id "1" is marked as read
        When action "2" is chosen
        And type "1" is selected
        Then system will respond with "Visualization of Quick sort"

    Scenario: List of not read entries contains all not read books
       When book with title "Database System Concepts", author "Henry F. Fort", ISBN "007-124476-X" and comment "Selkeä" is added
       When action "2" is chosen
       And type "2" is selected
       Then system will respond with "Database System Concepts"

    Scenario: List of not read entries contains all not read videos
       When video with title "Crash Course Computer Science Preview", url "https://youtu.be/tpIctyqH29Q", duration "2:44" and comment "Kiva" is added
       When action "2" is chosen
       And type "2" is selected
       Then system will respond with "Crash Course Computer Science Preview"