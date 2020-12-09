Feature: as a user I want to be able to add a video

    Scenario: A video with title and an url is added
        Given action "1" is chosen
        And type "2" is selected
        When title "Crash Course Computer Science Preview" and url "https://youtu.be/tpIctyqH29Q" is entered
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"

    Scenario: A video with title and no url is added
        Given action "1" is chosen
        And type "2" is selected
        When title "Visualization of Quick sort" and url "" is entered
        Then system will respond with "Lukuvinkin lisääminen epäonnistui"
