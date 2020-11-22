package kapistelykirjasto.domain;

import java.util.ArrayList;

public interface Application {
    /**
    * Creates an entry with the given title to the database.
    * @param title
    * @return true, if the entry was successfully created, false otherwise.
    */
    public boolean createEntry(String name);

    public ArrayList<Entry>getEntries();
}
