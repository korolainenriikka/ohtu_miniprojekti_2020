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
        io.print("*  - X: poistu sovelluksesta      *");
        io.print("***********************************");
        io.print("");
    }

    private void printActions() {
        io.print("- 0: tulosta valikko");
        io.print("- 1: lisää lukuvinkki");
        io.print("- 2: näytä lukuvinkit");
        io.print("- X: poistu sovelluksesta");
    }

    private void addEntry() {
        String entryTitle = io.readLine("Syötä lukuvinkin otsikko:");
        if (app.createEntry(entryTitle)) {
        	io.print("Lukuvinkki lisätty onnistuneesti");
        } else {
        	io.print("Lukuvinkin lisääminen epäonnistui");
        }
    }

    private void getEntries() {
        if (app.getEntries().isEmpty()) {
            io.print("Ei lisättyjä lukuvinkkejä");
        }

        for (int i = 0; i < app.getEntries().size(); i++) {
            io.print(app.getEntries().get(i).getTitle());
        }
    }
}
