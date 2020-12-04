package kapistelykirjasto.ui.cli;

import java.util.ArrayList;

import kapistelykirjasto.domain.Application;
import kapistelykirjasto.domain.Entry;
import kapistelykirjasto.ui.IO;

public class MarkAsReadAction implements Action {
	
	private IO io;
	private Application app;

	public MarkAsReadAction(IO io, Application app) {
		this.io = io;
		this.app = app;
	}
	
	@Override
	public void run() {
		ArrayList<Entry> notReadEntries = app.getNotReadEntries();
		Util.printEnumeratedList(io, app.getNotReadEntries(), "Olet lukenut jo kaikki lukuvinkit");

		if (notReadEntries.size() > 0) {
			String index = io.readLine("Syötä luetun lukuvinkin numero: ");
			if (!Util.isValidIndex(index, notReadEntries)) {
				io.print("Virheellinen syöte");
				return;
			}
			Entry entry = notReadEntries.get(Integer.valueOf(index) - 1);
			if (entry.getType() == Entry.Type.BOOK) {
				markBookAsRead(entry.getId());
			} else if (entry.getType() == Entry.Type.VIDEO) {
				markVideoAsRead(entry.getId());
			}
		}
	}
	
	private void markBookAsRead(int id) {

		if (this.app.markBookAsRead(id)) {
			io.print("Kirja merkitty luetuksi");
		} else {
			io.print("Kirjan merkkaaminen luetuksi epäonnistui");
		}
	}

	private void markVideoAsRead(int id) {

		if (this.app.markVideoAsRead(id)) {
			io.print("Video merkitty luetuksi");
		} else {
			io.print("Videon merkkaaminen luetuksi epäonnistui");
		}
	}
}
