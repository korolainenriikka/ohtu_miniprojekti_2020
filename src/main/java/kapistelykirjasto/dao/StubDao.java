package kapistelykirjasto.dao;

import java.util.ArrayList;

public class StubDao implements Dao {

    private ArrayList<Entry> entryes = new ArrayList<>();
    private boolean closed = false;

    @Override
    public boolean createEntry(Entry entry) {

        if (!closed) {
            entryes.add(entry);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public boolean sameTitleAlreadyExists(String title) {

        if (!closed) {

            if (entryes.equals(title)) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @Override
    public void close() {

        this.closed = true;
    }
}
