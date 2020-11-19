package kapistelykirjasto.domain;

import java.util.Random;

import kapistelykirjasto.dao.Entry;

import kapistelykirjasto.dao.SQLiteDao;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {

    private ApplicationLogic logic;

    @Before
    public void setUp() {

        this.logic = new ApplicationLogic(new SQLiteDao("logic_test_database.db"));
    }

    @Test
    public void createsAnEntry() {
        Random r = new Random();

        boolean entryAdded = this.logic.createEntry("Testi" + r.nextInt(100000));
        assertTrue(entryAdded);
    }

    @Test
    public void creatingEmptyEntryDoesNotWork() {

        boolean entryAdded = this.logic.createEntry("");
        assertFalse(entryAdded);
    }

    @Test
    public void invalidNameReturnFalseWhenValidName() {

        Random r = new Random();
        String name = "testi" + r.nextInt(100000);

        boolean invalidName = this.logic.invalidName(name);
        assertFalse(invalidName);
    }

    @Test
    public void invalidNameReturnTrueWhenZeroLengthName() {

        String name = "";

        boolean invalidName = this.logic.invalidName(name);
        assertTrue(invalidName);

        String message = this.logic.getError();
        assertEquals(message, "Otsikko ei voi olla tyhjä");
    }

    @Test
    public void addingSameTitleThatAlreadyExistsDoesNotWork() {

        Random r = new Random();
        String name = "Testinimi" + r.nextInt(100000);

        boolean entryAdded = this.logic.createEntry(name);
        assertTrue(entryAdded);

        boolean entryAdded2 = this.logic.createEntry(name);
        assertFalse(entryAdded2);

        boolean invalidName = this.logic.invalidName(name);
        assertTrue(invalidName);

        String message = this.logic.getError();
        assertEquals(message, "Otsikko on jo kirjastossa");
    }
}
