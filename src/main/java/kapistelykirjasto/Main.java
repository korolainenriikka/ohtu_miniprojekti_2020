package kapistelykirjasto;

import kapistelykirjasto.ui.*;
import kapistelykirjasto.domain.*;

public class Main {
    public static void main(String[] args) {
        Application app = new ApplicationLogic();
        IO io = new ConsoleIO();
        CLI userInterface = new CLI(app, io);
        userInterface.run();
    }
}
