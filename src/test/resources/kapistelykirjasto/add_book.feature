Feature: as a user I want to be able to add a book

    Scenario: A book with title, author, ISBN and comment is added
        Given action "1" is chosen
        And type "1" is selected
        When title "Hello Ruby!", author "Linda Liukas", ISBN "032-135522-K" and comment "a cool book!" is entered
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"

    Scenario: A book with title, author and ISBN is added
        Given action "1" is chosen
        And type "1" is selected
        When title "Database System Concepts", author "Henry F. Fort", ISBN "007-124476-X" and no comment is entered
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"

    Scenario: A book without title is not added
        Given action "1" is chosen
        And type "1" is selected
        When no title, author "Linda Liukas", ISBN "032-135522-K" and comment "a cool book!" is entered
        Then system will respond with "Lukuvinkin lisääminen epäonnistui"