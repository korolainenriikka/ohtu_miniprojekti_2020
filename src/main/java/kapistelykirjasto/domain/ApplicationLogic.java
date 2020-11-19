package kapistelykirjasto.domain;

import kapistelykirjasto.dao.Dao;
import kapistelykirjasto.dao.Entry;
import kapistelykirjasto.dao.SQLiteDao;

public class ApplicationLogic implements Application {
    
    private Dao dao;
    private String error;
    
    public ApplicationLogic(String fileName) {
        this.dao = new SQLiteDao(fileName);
    }
    
    @Override
    public boolean createEntry(String name){
        Entry entry = new Entry(name);
        if (this.dao.createEntry(entry)){
            this.error = "Otsikkoa ei lisätty";
            return false;
        }
        
        return true;
    }

}