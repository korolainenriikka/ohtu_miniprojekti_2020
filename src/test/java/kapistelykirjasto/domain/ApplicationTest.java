package kapistelykirjasto.domain;

import java.util.Random;
import kapistelykirjasto.dao.Entry;
import static org.junit.Assert.assertTrue;

import kapistelykirjasto.dao.SQLiteDao;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {
    
    private ApplicationLogic logic;
    
    @Before
    public void setUp(){
        this.logic = new ApplicationLogic(new SQLiteDao("logic_test_database.db"));
    }
    
    @Test
    public void createsAnEntry() {
        Random r = new Random();
        
        boolean entryAdded = this.logic.createEntry("Testi" + r.nextInt(10000));
        assertTrue(entryAdded);
    }
    
    @Test
    public void wontCreateTooShortEntry() {

    }

   
}
