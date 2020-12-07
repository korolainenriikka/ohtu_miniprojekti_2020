package kapistelykirjasto.ui.cli;

import kapistelykirjasto.domain.Application;
import kapistelykirjasto.domain.Entry;
import kapistelykirjasto.ui.IO;
import kapistelykirjasto.ui.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class CLI implements UserInterface {

    private Application app;
    private IO io;

    private HashMap<String, Action> topLevelActions;

    public CLI(Application app, IO io) {
        this.app = app;
        this.io = io;

        this.topLevelActions = new HashMap<String, Action>();
        this.topLevelActions.put("0", this::printActions);
        this.topLevelActions.put("1", new AddEntryAction(io, app));
        this.topLevelActions.put("2", new ViewEntriesAction(io, app));
        this.topLevelActions.put("3", new DeleteEntryAction(io, app));
        this.topLevelActions.put("4", new EditEntryAction(io, app));
        this.topLevelActions.put("5", new MarkAsReadAction(io, app));
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
}



