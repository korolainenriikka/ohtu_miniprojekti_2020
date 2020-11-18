package kapistelykirjasto.ui;

import kapistelykirjasto.domain.Application;

public class CLI implements UserInterface {

    private Application app;

    public CLI(Application app) {
        this.app = app;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException();
    }
}
