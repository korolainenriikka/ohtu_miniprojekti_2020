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

            for (int i = 0; i < entryes.size() - 1; i++) {
                if (entryes.get(i).getTitle().equals(title)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }


    @Override
    public void close() {

        this.closed = true;
    }

    public ArrayList listAll() {

        return entryes;
    }
}
