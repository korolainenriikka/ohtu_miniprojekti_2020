package kapistelykirjasto.domain;

import kapistelykirjasto.dao.Dao;
import kapistelykirjasto.dao.Entry;
import kapistelykirjasto.dao.SQLiteDao;

public class ApplicationLogic implements Application {
    
    private Dao dao;
    
    public ApplicationLogic(Dao dao) {
        this.dao = dao;
    }
    
    @Override
    public boolean createEntry(String name){
        Entry entry = new Entry(name);
        if (name.length() == 0 || !this.dao.createEntry(entry)) {
        	return false;
        }
        return true;
    }
}