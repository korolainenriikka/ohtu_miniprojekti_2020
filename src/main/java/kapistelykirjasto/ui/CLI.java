package kapistelykirjasto.ui;

import kapistelykirjasto.domain.Application;
import kapistelykirjasto.domain.Book;
import kapistelykirjasto.domain.Entry;
import kapistelykirjasto.domain.Video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CLI implements UserInterface {

    private Application app;
    private IO io;

    private HashMap<String, Action> topLevelActions;

    interface Action {
        public void run();
    }

    public CLI(Application app, IO io) {
        this.app = app;
        this.io = io;

        this.topLevelActions = new HashMap<String, Action>();
        this.topLevelActions.put("0", this::printActions);
        this.topLevelActions.put("1", this::addEntry);
        this.topLevelActions.put("2", this::getEntries);
        this.topLevelActions.put("3", this::deleteEntry);
        this.topLevelActions.put("4", this::editEntry);
        this.topLevelActions.put("5", this::markEntryAsRead);

    }

    @Override
    public void run() {
        printWelcomeMessage();
        while (io.hasNextLine()) {
            String action = io.readLine("\nAnna toiminto: ");
            if (action.equals("X")) {
                io.print("suljetaan");
                break;
            } else if (this.topLevelActions.containsKey(action)) {
                this.topLevelActions.get(action).run();
            } else {
                io.print("epäkelpo toiminto");
            }
        }
    }

    private void printWelcomeMessage() {
        io.print("**************************************");
        io.print("* Tervetuloa käpistelykirjastoon!    *");
        io.print("*                                    *");
        io.print("*  Toiminnot:                        *");
        io.print("*  - 0: tulosta valikko              *");
        io.print("*  - 1: lisää lukuvinkki             *");
        io.print("*  - 2: näytä lukuvinkit             *");
        io.print("*  - 3: poista lukuvinkki            *");
        io.print("*  - 4: muokkaa lukuvinkkiä          *");
        io.print("*  - 5: merkitse lukuvinkki luetuksi *");
        io.print("*  - X: poistu sovelluksesta         *");
        io.print("**************************************");
        io.print("");
    }

    private void printActions() {
        io.print("- 0: tulosta valikko");
        io.print("- 1: lisää lukuvinkki");
        io.print("- 2: näytä lukuvinkit");
        io.print("- 3: poista lukuvinkki");
        io.print("- 4: muokkaa lukuvinkkiä");
        io.print("- 5: merkitse lukuvinkki luetuksi");
        io.print("- X: poistu sovelluksesta");
    }

    private void addEntry() {
        String typeOfEntryAdded = io.readLine("[1]: lisää kirja \n[2]: lisää video");
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

        if (app.createBook(title, comment, author, isbn)) {
            io.print("Lukuvinkki lisätty onnistuneesti");
        } else {
            io.print("Lukuvinkin lisääminen epäonnistui");
        }
    }

    private void addVideo() {
        String title = io.readLine("Syötä videon nimi:");
        String url = io.readLine("Syötä videon url-osoite:");
        String duration = io.readLine("Syötä videon kesto (vapaaehtoinen):");
        String comment = io.readLine("Syötä kommentti (vapaavalintainen):");

        if (app.createVideo(title, comment, url, duration)) {
            io.print("Lukuvinkki lisätty onnistuneesti");
        } else {
            io.print("Lukuvinkin lisääminen epäonnistui");
        }
    }

    private void getEntries() {
        ArrayList<Entry> entries = app.getEntries();

        if (entries.isEmpty()) {
            io.print("Ei lisättyjä lukuvinkkejä");
        } else {
            io.print("Lukuvinkit: ");
            for (int i = 0; i < entries.size(); i++) {
                io.print(entries.get(i).toString());
            }
        }
        filterList();
    }

    private void filterList() {
        while (io.hasNextLine()) {
            String typeOfFilter = io.readLine(
                    "\n Suodata listaa:\n[1]: luetut \n[2]: lukemattomat \n[X]: poistu");
            if (typeOfFilter.equals("1")) {
                printEntriesWithNumbers(this.app.getReadEntries(), "ei luettuja lukuvinkkejä");
            } else if (typeOfFilter.equals("2")) {
                printEntriesWithNumbers(this.app.getNotReadEntries(), "Olet lukenut jo kaikki lukuvinkit");
            } else if (typeOfFilter.equals("X")) {
                break;
            } else {
                io.print("epäkelpo toiminto");
            }
        }
    }

    private void printEntriesWithNumbers(List<Entry> entries, String errormessage) {
        if (errormessage != "" && entries.isEmpty()) {
            io.print(errormessage);
        }
        for (int i = 0; i < entries.size(); i++) {
            io.print("[" + (i + 1) + "]: " + entries.get(i).toString());
        }
    }

    private void deleteEntry() {
        ArrayList<Entry> entries = app.getEntries();
        printEntriesWithNumbers(entries, "");

        String index = io.readLine("Syötä poistettavan lukuvinkin numero:");

        if (validIndexGiven(index, entries)) {
            if (this.app.deleteEntry(entries.get(Integer.valueOf(index) - 1))) {
                io.print("Lukuvinkin poistaminen onnistui");
            } else {
                io.print("Lukuvinkin poistaminen ei onnistunut");
            }
        } else {
            io.print("Virhe: Vääränlainen syöte");
        }
    }

    private boolean validIndexGiven(String input, List<Entry> entries) {
        return parsable(input) && Integer.valueOf(input) > 0 && Integer.valueOf(input) <= entries.size();
    }

    private boolean parsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void editEntry() {
        ArrayList<Entry> entries = app.getEntries();
        printEntriesWithNumbers(entries, "");
        String index = io.readLine("Syötä muokattavan lukuvinkin numero: ");

        if (!validIndexGiven(index, entries)) {
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


    private void markEntryAsRead() {

        ArrayList<Entry> notReadEntries = app.getNotReadEntries();
        printEntriesWithNumbers(app.getNotReadEntries(), "Olet lukenut jo kaikki lukuvinkit");

        if (notReadEntries.size() > 0) {
            String index = io.readLine("Syötä luetun lukuvinkin numero: ");
            if (!validIndexGiven(index, notReadEntries)) {
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
}



