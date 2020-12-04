package kapistelykirjasto.ui.cli;

import java.util.ArrayList;

import kapistelykirjasto.domain.Application;
import kapistelykirjasto.domain.Book;
import kapistelykirjasto.domain.Entry;
import kapistelykirjasto.domain.Video;
import kapistelykirjasto.ui.IO;

public class EditEntryAction implements Action {

	private IO io;
	private Application app;

	public EditEntryAction(IO io, Application app) {
		this.io = io;
		this.app = app;
	}
	
	@Override
	public void run() {
        ArrayList<Entry> entries = app.getEntries();
        Util.printEnumeratedList(io, entries, "");
        String index = io.readLine("Syötä muokattavan lukuvinkin numero: ");

        if (!Util.isValidIndex(index, entries)) {
            io.print("Virheellinen syöte");
            return;
        }
        Entry entry = entries.get(Integer.valueOf(index) - 1);
        int entryId = entry.getId();

        if (entry.getType() == Entry.Type.BOOK) {
            Book book = ((Book) entry);
            editBook(book, entryId);
        } else if (entry.getType() == Entry.Type.VIDEO) {
            Video video = ((Video) entry);
            editVideo(video, entryId);
        }
    }

    private void editBook(Book book, int index) {

        io.print("Nykyiset tiedot:\n" + "        kirjan nimi: " + book.toString());
        String title = io.readLine("Syötä kirjan nimi:");
        String author = io.readLine("Syötä kirjan kirjoittaja:");
        String isbn = io.readLine("Syötä ISBN:");
        String comment = io.readLine("Syötä kommentti (vapaavalintainen):");

        if (app.editBook(index, title, comment, author, isbn)) {
            io.print("Lukuvinkki muokattu onnistuneesti");
        } else {
            io.print("Lukuvinkin muokkaaminen epäonnistui");
        }
    }

    private void editVideo(Video video, int index) {

        io.print("Nykyiset tiedot:\n" + "        videon nimi: " + video.toString());
        String title = io.readLine("Syötä videon nimi:");
        String duration = io.readLine("Syötä videon kesto (vapaavalintainen)");
        String url = io.readLine("Syötä videon url:");
        String comment = io.readLine("Syötä kommentti (vapaavalintainen):");

        if (app.editVideo(index, title, comment, url, duration)) {
            io.print("Lukuvinkki muokattu onnistuneesti");
        } else {
            io.print("Lukuvinkin muokkaaminen epäonnistui");
        }
    }
	
}
