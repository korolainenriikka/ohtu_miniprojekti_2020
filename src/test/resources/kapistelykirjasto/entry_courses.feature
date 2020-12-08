Feature: The user can create courses and add them to an entry

	Scenario: When adding a new entry courses added previously to the application are listed
	    Given course with code "1234" and name "Ohtu" exists
	    And action "1" is chosen
	    And action "1" is chosen
	    And user has added book inputs "lean manifesto", "lean manifesto author", "123" and "read this!!"
        Then input prompt contains "[1]: 1234 Ohtu"

	Scenario: The user can create a new course and associate the created course to an entry
	    Given action "1" is chosen
        And action "2" is chosen
        And user has added video inputs "Java magic", "www.javamagicworld.com", "1:00:00" and "watch this!!"
        And action "X" is chosen
        And course code "TKT123" and course name "Java pro course" are entered
        When course number input "1" is added
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"
		
	Scenario: The user can create a new entry without adding associated courses
	    Given action "1" is chosen
        And action "2" is chosen
        And user has added video inputs "Java magic", "www.javamagicworld.com", "1:00:00" and "watch this!!"
        When course number input "" is added
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"
	
	Scenario: The user can associate previously added courses to a new entry added
	    Given course with code "1234" and name "Ohtu" exists
	    And course with code "12345" and name "Ohtuprojekti" exists
	    And action "1" is chosen
        And action "1" is chosen
        And user has added book inputs "lean manifesto", "lean manifesto author", "123" and "read this!!"
        When course number input "1,2" is added
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"

	Scenario: A new course without course code and name is not added
	    Given action "1" is chosen
    	And action "1" is chosen
    	And user has added book inputs "lean manifesto", "lean manifesto author", "123" and "read this!!"
    	And action "X" is chosen
    	When course code "" and course name "" are entered
    	Then system will respond with "Syötä indeksit pilkulla erotettuna:"

	Scenario: The user is asked for related courses again if user gives an invalid course number input
	     Given action "1" is chosen
         And action "1" is chosen
         And user has added book inputs "lean manifesto", "lean manifesto author", "123" and "read this!!"
         When course number input "123" is added
         Then system will respond with "Syötä indeksit pilkulla erotettuna:"

