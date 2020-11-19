package kapistelykirjasto.dao;

import java.util.ArrayList;

public class StubDao implements Dao {

    private ArrayList<Entry> entryes = new ArrayList<>();

    @Override
    public boolean createEntry(Entry entry) {

        if (entry.getTitle().equals(entry.getTitle())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sameTitleAlreadyExists(String title) {
        return false;
    }

    @Override
    public void close() {

    }
}
