package kapistelykirjasto.domain;

import kapistelykirjasto.dao.Dao;

import java.util.ArrayList;

public class ApplicationLogic implements Application {
    
    private Dao dao;
    
    public ApplicationLogic(Dao dao) {
        this.dao = dao;
    }
    
    @Override
    public boolean createEntry(String name) {
        Entry entry = new Entry(name);
        if (name.length() == 0 || !this.dao.createEntry(entry)) {
        	return false;
        }
        return true;
    }

    @Override
    public ArrayList<Entry> getEntries() {
        ArrayList<Entry>entries = new ArrayList<>();
        entries = this.dao.getEntries();

        return entries;
    }
}