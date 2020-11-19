package kapistelykirjasto.domain;

import java.util.Random;
import kapistelykirjasto.dao.Entry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import kapistelykirjasto.dao.SQLiteDao;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {
    
    private ApplicationLogic logic;
    
    @Before
    public void setUp(){
<<<<<<< HEAD

        this.logic = new ApplicationLogic("logic_test_database.db");
=======
        this.logic = new ApplicationLogic(new SQLiteDao("logic_test_database.db"));
>>>>>>> 6f2022845b6ae0991cf6ead01822f209b01ab9bd
    }
    
    @Test
    public void createsAnEntry() {
        Random r = new Random();
        
        boolean entryAdded = this.logic.createEntry("Testi" + r.nextInt(10000));
        assertTrue(entryAdded);
    }
    
    @Test
    public void wontCreateTooShortEntry() {

        boolean entryAdded = this.logic.createEntry("");
        assertFalse(entryAdded);
    }
}
