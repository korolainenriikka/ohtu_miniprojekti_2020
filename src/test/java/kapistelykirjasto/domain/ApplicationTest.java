package kapistelykirjasto.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {
    
    private ApplicationLogic logic;
    
    @Before
    public void setUp(){
        this.logic = new ApplicationLogic("logic_test_database.db");
    }

    @Test
    public void hello() {
    }

   
}
