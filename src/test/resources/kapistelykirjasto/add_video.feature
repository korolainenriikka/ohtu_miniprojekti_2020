Feature: as a user I want to be able to add a video

    Scenario: A video with title and an url is added
        Given action "1" is chosen
        And type "3" is selected
        When title "a video" and url "www.video.com" is entered
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"

    Scenario: A video with title and no url is added
        Given action "1" is chosen
        And type "3" is selected
        When title "a video" and url "" is entered
        Then system will respond with "Lukuvinkin lisääminen epäonnistui"
