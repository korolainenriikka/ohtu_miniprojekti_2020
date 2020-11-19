package kapistelykirjasto;

import kapistelykirjasto.dao.Dao;
import kapistelykirjasto.dao.SQLiteDao;
import kapistelykirjasto.ui.*;
import kapistelykirjasto.domain.*;

public class Main {
    public static void main(String[] args) {
        String dbFileName = "library.db";
        Dao dao = new SQLiteDao(dbFileName);
        Application app = new ApplicationLogic(dao);
        IO io = new ConsoleIO();
        CLI userInterface = new CLI(app, io);
        userInterface.run();
    }
}
