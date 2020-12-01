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
    }

    @Override
    public void run() {
        printWelcomeMessage();
        while (io.hasNextLine()) {
            String action = io.readLine("Anna toiminto: ");
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

        String index = io.readLine("Syötä poistettavan lukuvinkin numero: ");

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

        int selectedEntryIndex = Integer.parseInt(index);
        Entry e = entries.get(selectedEntryIndex - 1);

        if (e.getType() == Entry.Type.BOOK) {
            Book b = ((Book) e);
            editBook(b, selectedEntryIndex - 1);
        } else if (e.getType() == Entry.Type.VIDEO) {
            editVideo(e);
        }
    }

    private void editBook(Book b, int index) {
        String[] fields = new String[]{"nimi", "kirjailija", "ISBN", "kommentti"};
        String[] currentValues = new String[]{b.getTitle(), b.getAuthor(), b.getISBN(), b.getComment()};

        for (int i = 0; i < fields.length; i++ ) {
            io.print("[" + (i + 1) + "]" + fields[i] + ": " + currentValues[i]);
        }

        String fieldsToEdit = io.readLine("Syötä muokattavien kenttien numerot: (1,2,3)");
        if (validFieldSelectInputGiven(fieldsToEdit)) {

        }
            /*      näin voisi muokata yksittäistä tietokenttää
            int selectedFieldIndex;
            while (true) {
                String index = io.readLine(
                        "[1] otsikko\n" +
                                "[2] kommentti\n" +
                                "[3] kirjoittaja\n" +
                                "[4] ISBN\n" +
                                "Syötä tietokentän numero, jota haluat muokata: ");
                selectedFieldIndex = Integer.valueOf(index);
                if (selectedFieldIndex > 0 && selectedFieldIndex <= 4) {
                    break;
                }
                io.print("Vääränlainen syöte");
            }
            String newValue = io.readLine("Syötä tietokentän uusi arvo: ");
             */

        io.print("Nykyiset tiedot:\n" + b.toString());
        String title = io.readLine("Syötä kirjan nimi:");
        String author = io.readLine("Syötä kirjan kirjoittaja:");
        String isbn = io.readLine("Syötä ISBN:");
        String comment = io.readLine("Syötä kommentti (vapaavalintainen):");

        if (app.editBook( index, title, comment, author, isbn)) {
            io.print("Lukuvinkki muokattu onnistuneesti");
        } else {
            io.print("Lukuvinkin muokkaaminen epäonnistui");
        }
    }

    private boolean validFieldSelectInputGiven(String input) {
        String[] inputSplitted = input.split(",");
        try {
            for (String fieldNo: inputSplitted) {
                Integer.parseInt(fieldNo);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void editVideo(Entry e) {
        io.print("not implemented :(");
    }
}

