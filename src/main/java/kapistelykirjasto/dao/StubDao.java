package kapistelykirjasto.dao;

import java.util.ArrayList;

public class StubDao implements Dao {

    private ArrayList<Entry> entries = new ArrayList<>();
    private boolean closed = false;

    @Override
    public boolean createEntry(Entry entry) {
    	if (closed || existsEntry(entry)) {
    		return false;
    	}

        entries.add(entry);
        return true;
    }

    private boolean existsEntry(Entry entry) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).equals(entry)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void close() {

        this.closed = true;
    }

    public ArrayList<Entry> getEntries() {
        return this.entries;
    }
}
