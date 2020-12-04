package kapistelykirjasto.ui.cli;

import java.util.ArrayList;

import kapistelykirjasto.domain.Application;
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
                    "\n Suodata listaa:\n[1]: luetut \n[2]: lukemattomat \n[X]: poistu");
            if (typeOfFilter.equals("1")) {
                Util.printList(io, this.app.getReadEntries(), "ei luettuja lukuvinkkejä");
            } else if (typeOfFilter.equals("2")) {
                Util.printList(io, this.app.getNotReadEntries(), "Olet lukenut jo kaikki lukuvinkit");
            } else if (typeOfFilter.equals("X")) {
                break;
            } else {
                io.print("epäkelpo toiminto");
            }
        }
    }
}
