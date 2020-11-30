Feature: as a user I want to be able to add a book

    Scenario: A book with title, author, ISBN and comment is added
        Given action "1" is chosen
        And type "2" is selected
        When title "a Book", author "mr Author", ISBN "12345abc" and comment "a cool book!" is entered
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"

    Scenario: A book with title, author and ISBN is added
        Given action "1" is chosen
        And type "2" is selected
        When title "another Book", author "mr Author", ISBN "12345abcd" and no comment is entered
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"

    Scenario: A book without title is not added
        Given action "1" is chosen
        And type "2" is selected
        When no title, author "mr Author", ISBN "12345abc" and comment "a cool book!" is entered
        Then system will respond with "Lukuvinkin lisääminen epäonnistui"