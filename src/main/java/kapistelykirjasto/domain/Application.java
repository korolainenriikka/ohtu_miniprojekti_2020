package kapistelykirjasto.domain;

import kapistelykirjasto.dao.Entry;

public interface Application {
    
        /**
         * Creates an entry with the given title to the database.
         * @param title
         * @return true, if the entry was successfully created, false otherwise.
         */
        public boolean createEntry(String name);
        
        /**
         * Gets the error that occured 
         * @param title
         * @return String of the error
         */
        public String getError();
        
        /**
         * Sets the error to null.
         */
        public void setErrorNull();
}
