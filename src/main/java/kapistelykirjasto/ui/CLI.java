package kapistelykirjasto.ui;

import kapistelykirjasto.domain.Application;

import java.util.Scanner;

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
        String typeOfEntryAdded = io.readLine("[1]: lisää vain otsikko\n[2]: lisää kirja \n[3]: lisää video");
        if (typeOfEntryAdded.equals("1")) {
            addEntryWithTitle();
        } else if (typeOfEntryAdded.equals("2")) {
            addBook();
        } else if (typeOfEntryAdded.equals("3")) {
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

    private void addEntryWithTitle() {
        String entryTitle = io.readLine("Syötä lukuvinkin otsikko:");
        if (app.createEntry(entryTitle)) {
            io.print("Lukuvinkki lisätty onnistuneesti");
        } else {
            io.print("Lukuvinkin lisääminen epäonnistui");
        }
    }

    private void getEntries() {

        io.print("Kirjavinkit: ");
        if (app.getBooks().isEmpty()) {
            io.print("Ei lisättyjä kirjavinkkejä");
        }
        for (int i = 0; i < app.getBooks().size(); i++) {
            io.print(app.getBooks().get(i).getTitle());
        }

        io.print("Videovinkit: ");
        if (app.getVideos().isEmpty()) {
            io.print("Ei lisättyjä videovinkkejä");
        }
        for (int i = 0; i < app.getVideos().size(); i++) {
            io.print(app.getVideos().get(i).getTitle());
        }
    }

    private void deleteEntry() {
        String entryTitle = io.readLine("Syötä poistettavan lukuvinkin otsikko:");
        if (app.deleteEntryBasedOnTitle(entryTitle)) {
            io.print("Lukuvinkki poistettu onnistuneesti");
        } else {
            io.print("Lukuvinkin poistaminen epäonnistui");
        }
    }
}
