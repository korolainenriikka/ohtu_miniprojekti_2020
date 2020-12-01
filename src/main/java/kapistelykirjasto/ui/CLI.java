package kapistelykirjasto.ui;

import kapistelykirjasto.domain.Application;
import kapistelykirjasto.domain.Book;
import kapistelykirjasto.domain.Entry;
import kapistelykirjasto.domain.Video;

import java.util.ArrayList;
import java.util.List;

public class CLI implements UserInterface {

    private Application app;
    private IO io;

    public CLI(Application app, IO io) {
        this.app = app;
        this.io = io;
    }

    @Override
    public void run() {
        printWelcomeMessage();
        while (io.hasNextLine()) {
            String action = io.readLine("Anna toiminto: ");
            if (action.equals("X")) {
                io.print("suljetaan");
                break;
            } else if (action.equals("0")) {
                printActions();
            } else if (action.equals("1")) {
                addEntry();
            } else if (action.equals("2")) {
                getEntries();
            } else if (action.equals("3")) {
                deleteEntry();
            } else if (action.equals("4")) {
                editEntry();
            } else {
                io.print("epäkelpo toiminto");
            }
        }
    }

    private void printWelcomeMessage() {
        io.print("***********************************");
        io.print("* Tervetuloa käpistelykirjastoon! *");
        io.print("*                                 *");
        io.print("*  Toiminnot:                     *");
        io.print("*  - 0: tulosta valikko           *");
        io.print("*  - 1: lisää lukuvinkki          *");
        io.print("*  - 2: näytä lukuvinkit          *");
        io.print("*  - 3: poista lukuvinkki         *");
        io.print("*  - 4: muokkaa lukuvinkkiä       *");
        io.print("*  - X: poistu sovelluksesta      *");
        io.print("***********************************");
        io.print("");
    }

    private void printActions() {
        io.print("- 0: tulosta valikko");
        io.print("- 1: lisää lukuvinkki");
        io.print("- 2: näytä lukuvinkit");
        io.print("- 3: poista lukuvinkki");
        io.print("- 4: muokkaa lukuvinkkiä");
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
    }

    private void printEntriesWithNumbers(List<Entry> entries) {
        for (int i = 0; i < entries.size(); i++) {
            io.print("[" + (i + 1) + "]: " + entries.get(i).toString());
        }
    }

    private void deleteEntry() {
        ArrayList<Entry> entries = app.getEntries();
        printEntriesWithNumbers(entries);

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
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private void editEntry() {
        ArrayList<Entry> entries = app.getEntries();
        printEntriesWithNumbers(entries);
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

    private void printBookData(Book book) {

        String[] fields = new String[]{"nimi", "kirjailija", "ISBN", "kommentti"};
        String[] currentValues = new String[]{book.getTitle(), book.getAuthor(), book.getISBN(), book.getComment()};
        for (int i = 0; i < fields.length; i++) {
            io.print("[" + (i + 1) + "] " + fields[i] + ": " + currentValues[i]);
        }
    }

    private void printVideoData(Video video) {

        String[] fields = new String[]{"nimi", "kesto", "url", "kommentti"};
        String[] currentValues = new String[]{video.getTitle(), video.getDuration(), video.getUrl(), video.getComment()};
        for (int i = 0; i < fields.length; i++) {
            io.print("[" + (i + 1) + "] " + fields[i] + ": " + currentValues[i]);
        }
    }

    private void editBook2(Book book, int index) {

        /* näin voisi muokata yksittäistä tietokenttää
        printBookData(book);
        String fieldsToEdit = io.readLine("Syötä muokattavien kenttien numerot: (1,2,3,4)");

        int selectedFieldIndex;
        String selectedField = "";

        while (true) {
            selectedField = io.readLine("[1] otsikko\n" + "[2] kommentti\n" + "[3] kirjoittaja\n" +
                            "[4] ISBN\n" + "Syötä tietokentän numero, jota haluat muokata: ");
            selectedFieldIndex = Integer.valueOf(selectedField);
            if (selectedFieldIndex > 0 && selectedFieldIndex <= 4) {
                break;
            }
            io.print("Vääränlainen syöte");
        }
        String newValue = io.readLine("Syötä tietokentän uusi arvo: "); */
    }

    private void editVideo2(Video video, int index) {

        /*     näin voisi muokata yksittäistä tietokenttää
        String fieldsToEdit = io.readLine("Syötä muokattavien kenttien numerot: (1,2,3,4)");

        int selectedFieldIndex;
        String selectedField = "";

        while (true) {
            selectedField = io.readLine("[1] otsikko\n" + "[2] kesto\n" + "[3] url\n" +
                    "[4] kommentti\n" + "Syötä tietokentän numero, jota haluat muokata: ");
            selectedFieldIndex = Integer.valueOf(selectedField);
            if (selectedFieldIndex > 0 && selectedFieldIndex <= 4) {
                break;
            }
            io.print("Vääränlainen syöte");
        }
        String newValue = io.readLine("Syötä tietokentän uusi arvo: "); */
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

    private boolean validFieldSelectInputGiven(String input) {
        String[] inputSplitted = input.split(",");
        try {
            for (String fieldNo : inputSplitted) {
                Integer.parseInt(fieldNo);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

