Feature: The user can create courses and add them to an entry

	Scenario: The system asks for courses when creating a book
		When user creates a book
		Then system will respond with "Syötä kurssit"
		
	Scenario: The system asks for courses when creating a video
		When user creates a video
		Then system will respond with "Syötä kurssit"
		
	Scenario: The system shows existing courses when creating a book
		Given course with code "1234" and name "Ohtu" exists
		When user creates a book
		Then system will respond with "1234 Ohtu"
		
	Scenario: The system shows existing courses when creating a video
		Given course with code "1234" and name "Ohtu" exists
		And user is specifying the courses for a video
		Then system will respond with "1234 Ohtu"
	
	Scenario: The user can create a course while creating a book
		Given user is specifying the courses for a book
		When user creates a course
		Then system will respond with "Kurssi lisätty onnistuneesti"
		
	Scenario: The user can create a course while creating a video
		Given user is specifying the courses for a video
		When user creates a course
		Then system will respond with "Kurssi lisätty onnistuneesti"