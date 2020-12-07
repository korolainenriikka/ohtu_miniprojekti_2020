package kapistelykirjasto.ui.cli;

import java.util.ArrayList;

import kapistelykirjasto.domain.Application;
import kapistelykirjasto.domain.Entry;
import kapistelykirjasto.ui.IO;

public class DeleteEntryAction implements Action {
	
	private IO io;
	private Application app;

	public DeleteEntryAction(IO io, Application app) {
		this.io = io;
		this.app = app;
	}

	@Override
	public void run() {
        ArrayList<Entry> entries = app.getEntries();
        Util.printEnumeratedList(io, entries, "");
        
        String index = io.readLine("Syötä poistettavan lukuvinkin numero:");

        if (Util.isValidIndex(index, entries)) {
            if (this.app.deleteEntry(entries.get(Integer.valueOf(index) - 1))) {
                io.print("Lukuvinkin poistaminen onnistui");
            } else {
                io.print("Lukuvinkin poistaminen ei onnistunut");
            }
        } else {
            io.print("Virhe: Vääränlainen syöte");
        }
    }
}
