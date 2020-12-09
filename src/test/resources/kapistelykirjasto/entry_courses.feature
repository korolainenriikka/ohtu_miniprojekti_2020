Feature: The user can create courses and add them to an entry

	Scenario: When adding a new entry courses added previously to the application are listed
	    Given course with code "TKT10004" and name "Tietokantojen perusteet" exists
	    And action "1" is chosen
	    And action "1" is chosen
	    And user has added book inputs "Database System Concepts", "Fort", "007-124476-X" and "Read this!!"
        Then input prompt contains "[1]: TKT10004 Tietokantojen perusteet"

	Scenario: The user can create a new course and associate the created course to an entry
	    Given action "1" is chosen
        And action "2" is chosen
        And user has added video inputs "Crash Course Computer Science Preview", "https://youtu.be/tpIctyqH29Q", "1:00" and "Watch this!!"
        And action "X" is chosen
        And course code "TKT10001" and course name "Johdatus tietojenkäsittelytieteeseen" are entered
        When course number input "1" is added
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"
		
	Scenario: The user can create a new entry without adding associated courses
	    Given action "1" is chosen
        And action "2" is chosen
        And user has added video inputs "Visualization of Quick sort", "www.youtube.com", "2:56" and "Watch this!!"
        When course number input "" is added
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"
	
	Scenario: The user can associate previously added courses to a new entry added
	    Given course with code "TKT20006" and name "Ohjelmistotuotanto" exists
	    And course with code "TKT20007" and name "Ohjelmistotuotantoprojekti" exists
	    And action "1" is chosen
        And action "1" is chosen
        And user has added book inputs "Database System Concepts", "H. Fort", "007-124476-X" and "Read this!!"
        When course number input "1,2" is added
        Then system will respond with "Lukuvinkki lisätty onnistuneesti"

	Scenario: A new course without course code and name is not added
	    Given action "1" is chosen
    	And action "1" is chosen
    	And user has added book inputs "Hello Ruby!", "Linda Liukas", "032-135522-K" and "Read this!!"
    	And action "X" is chosen
    	When course code "" and course name "" are entered
    	Then system will respond with "Syötä indeksit pilkulla erotettuna:"

	Scenario: The user is asked for related courses again if user gives an invalid course number input
	     Given action "1" is chosen
         And action "1" is chosen
         And user has added book inputs "Elements of the Theory of Computation", "Lewis", "135-896577-E" and "Read this!!"
         When course number input "123" is added
         Then system will respond with "Syötä indeksit pilkulla erotettuna:"

