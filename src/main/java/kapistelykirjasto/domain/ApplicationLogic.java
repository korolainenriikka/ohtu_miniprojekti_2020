package kapistelykirjasto.domain;

import kapistelykirjasto.dao.Dao;
import kapistelykirjasto.dao.Entry;
import kapistelykirjasto.dao.SQLiteDao;

public class ApplicationLogic implements Application {
    
    private Dao dao;
    private String error;
    
    public ApplicationLogic(Dao dao) {
        this.dao = dao;
    }
    
    @Override
    public boolean createEntry(String name){
        Entry entry = new Entry(name);
        if(invalidName(name)){
            return false;
        }
        else if (!this.dao.createEntry(entry)){
            this.error = "Otsikkoa ei lisätty";
            return false;
        }
        return true;
    }

    private boolean invalidName(String name) {
        if (name.length() <= 0){
            this.error = "Otsikko ei voi olla tyhjä";
            return true;
        }
        return false;
    }

    @Override
    public String getError() {
        return this.error;
    }
    
    @Override
    public void setErrorNull() {
        this.error = null;
    }

}