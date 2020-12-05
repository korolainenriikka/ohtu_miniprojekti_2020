package kapistelykirjasto.ui.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import kapistelykirjasto.domain.Application;
import kapistelykirjasto.domain.Course;
import kapistelykirjasto.ui.IO;
import kapistelykirjasto.util.Result;

public class AddEntryAction implements Action {
	
	private IO io;
	private Application app;

	public AddEntryAction(IO io, Application app) {
		this.io = io;
		this.app = app;
	}
	
	@Override
	public void run() {
    	
        String typeOfEntryAdded = io.readLine("[1]: lisää kirja \n[2]: lisää video");
        io.print("Kurssi lisätty onnistuneesti");
        if (typeOfEntryAdded.equals("1")) {
            addBook();
        } else if (typeOfEntryAdded.equals("2")) {
            addVideo();
        } else {
            io.print("epäkelpo toiminto");
        }
    }

    private void addBook() {
        String title = io.readLine("Syötä kirjan nimi:");
        String author = io.readLine("Syötä kirjan kirjoittaja:");
        String isbn = io.readLine("Syötä ISBN:");
        String comment = io.readLine("Syötä kommentti (vapaavalintainen):");
        int[] courseIds = readCourses();

        Result<String, Integer> res = app.createBook(title, comment, author, isbn, courseIds);
        if (res.isValue()) {
        	io.print("Lukuvinkki lisätty onnistuneesti");
        } else {
        	io.print("Lukuvinkin lisääminen epäonnistui: " + res.getError());
        }
    }

    private void addVideo() {
        String title = io.readLine("Syötä videon nimi:");
        String url = io.readLine("Syötä videon url-osoite:");
        String duration = io.readLine("Syötä videon kesto (vapaaehtoinen):");
        String comment = io.readLine("Syötä kommentti (vapaavalintainen):");
        int[] courseIds = readCourses();

        Result<String, Integer> res = app.createVideo(title, comment, url, duration, courseIds);
        if (res.isValue()) {
        	io.print("Lukuvinkki lisätty onnistuneesti");
        } else {
        	io.print("Lukuvinkin lisääminen epäonnistui: " + res.getError());
        }
    }
    
    private int[] readCourses() {
    	do {
    		List<Course> courses = app.getCourses();
    		Util.printEnumeratedList(io, courses, "Ei lisättyjä kursseja");
        	io.print("[X]: luo uusi kurssi");
        	String courseInput = io.readLine("Syötä kurssit: ");
        	
        	if (courseInput.equals("X")) {
        		createCourse();
        	} else if (courseInput.equals("")) {
        		return new int[0];
        	} else if (courseInput.matches("([0-9]+,\\s*)*\\s*([0-9]+)?")) {
        		return parseCourseIds(courseInput, courses);
        	} else {
        		io.print("Virheellinen syöte");
        	}
    	} while (io.hasNextLine());
    	return new int[0];
    }
    
    private void createCourse() {
    	String courseCode = io.readLine("Syötä kurssin koodi:");
		String name = io.readLine("Syötä kurssin nimi:");
		
		if (app.createCourse(courseCode, name)) {
			io.print("Kurssi lisätty onnistuneesti");
		} else {
			io.print("Kurssin lisääminen epäonnistui");
		}
    }
    
    private int[] parseCourseIds(String input, List<Course> courses) {
    	if (!Arrays.stream(input.split(","))
				.allMatch(s -> Util.isValidIndex(s, courses))) {
			io.print("Virheellinen syöte");
		} else {
			return Arrays.stream(input.split(","))
					.mapToInt(Integer::parseInt)
					.map(i -> courses.get(i).getId())
					.toArray();
		}
    	return new int[0];
    }
}
