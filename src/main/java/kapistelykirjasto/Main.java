package kapistelykirjasto;

import kapistelykirjasto.dao.*;
import kapistelykirjasto.ui.*;
import kapistelykirjasto.ui.cli.CLI;
import kapistelykirjasto.domain.*;

public class Main {
    public static void main(String[] args) {
        String dbFileName = "library.db";
        BookDao bookDao = new SQLiteBookDao(dbFileName);
        VideoDao videoDao = new SQLiteVideoDao(dbFileName);
        Application app = new ApplicationLogic(bookDao, videoDao);
        IO io = new ConsoleIO();
        CLI userInterface = new CLI(app, io);
        userInterface.run();
    }
}
