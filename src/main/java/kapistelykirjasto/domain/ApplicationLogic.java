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
        if(invalidName(name)){
            return false;
        }
        else if (this.dao.createEntry(entry)){
            this.error = "Otsikkoa ei lisätty, virhe tietokantayhteydessä";
            return false;
        }
        return true;
    }

    @Override
    public boolean invalidName(String name) {
        if (this.dao.sameTitleAlreadyExists(name)){
            this.error= "Otsikko on jo kirjastossa";
            return false;
        }
        if (name.length() < 3){
            this.error = "Otsikon tulee olla ainakin kolme merkkiä";
            return false;
        }
        return true;
    }

    @Override
    public String getError() {
        return this.error;
    }

}