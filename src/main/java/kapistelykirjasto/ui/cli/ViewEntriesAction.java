package kapistelykirjasto.ui.cli;

import java.util.ArrayList;
import java.util.List;

import kapistelykirjasto.domain.Application;
import kapistelykirjasto.domain.Course;
import kapistelykirjasto.domain.Entry;
import kapistelykirjasto.ui.IO;

public class ViewEntriesAction implements Action {
	
	private IO io;
	private Application app;

	public ViewEntriesAction(IO io, Application app) {
		this.io = io;
		this.app = app;
	}
	
	@Override
	public void run() {
        ArrayList<Entry> entries = app.getEntries();
        Util.printList(io, entries, "Ei lisättyjä lukuvinkkejä");

        filterList();
    }

    private void filterList() {
        while (io.hasNextLine()) {
            String typeOfFilter = io.readLine(
                    "\n Suodata listaa:\n[1]: luetut \n[2]: lukemattomat \n[3]: kurssi \n[X]: poistu");
            if (typeOfFilter.equals("1")) {
                Util.printList(io, this.app.getReadEntries(), "ei luettuja lukuvinkkejä");
            } else if (typeOfFilter.equals("2")) {
                Util.printList(io, this.app.getNotReadEntries(), "Olet lukenut jo kaikki lukuvinkit");
            //} else if (typeOfFilter.equals("3")) {
            //    int[] courses = readCourses();
            //    Util.printList(io, this.app.getCourseEntries(int courseId), "ei luettuja lukuvinkkejä");
            } else if (typeOfFilter.equals("X")) {
                break;
            } else {
                io.print("epäkelpo toiminto");
            }
        }
    }

    private int[] readCourses() {
        do {
            List<Course> courses = app.getCourses();
            Util.printEnumeratedList(io, courses, "Ei lisättyjä kursseja");
            String courseInput = io.readLine("Syötä kurssit: ");

            if (courseInput.equals("")) {
                return new int[0];
            } else if (courseInput.matches("([0-9]+,\\s*)*\\s*([0-9]+)?")) {
                return Util.parseCourseIds(io, courseInput, courses);
            } else {
                io.print("Virheellinen syöte");
            }
        } while (io.hasNextLine());
        return new int[0];
    }
}
