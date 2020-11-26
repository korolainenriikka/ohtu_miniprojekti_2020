package kapistelykirjasto.domain;

import java.util.ArrayList;

public interface Application {
    /**
    * Creates an entry with the given title to the database.
    * @param title
    * @return true, if the entry was successfully created, false otherwise.
    */
    public boolean createEntry(String name);

    public boolean createBook(String title, String comment, String author, String ISBN);
    
    public boolean createVideo(String title, String comment, String url, String duration);

    public ArrayList<Entry>getEntries();

    public boolean deleteEntryBasedOnTitle(String title);
}
