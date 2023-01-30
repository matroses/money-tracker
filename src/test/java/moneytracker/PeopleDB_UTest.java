package moneytracker;

import moneytracker.model.Person;
import moneytracker.model.db.PeopleDB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PeopleDB_UTest {

    @Before
    public void initialize() {
        PeopleDB db = PeopleDB.getInstance();

        // Clean up the database
        for (Person p : db.getAllPeople()) {
            db.removePerson(p);
        }
    }

    @Test
    public void test_addPerson() {
        Person p = new Person("Jens", "De Hoog");

        PeopleDB db = PeopleDB.getInstance();
        Assert.assertTrue(db.getAllPeople().isEmpty());

        db.addPerson(p);
        Assert.assertEquals(1, db.getAllPeople().size());

        Person retrieved = db.getAllPeople().get(0);
        Assert.assertEquals(p, retrieved);
    }

    @Test
    public void test_removePerson() {
        Person p = new Person("Ali", "Anwar");

        PeopleDB db = PeopleDB.getInstance();
        Assert.assertTrue(db.getAllPeople().isEmpty());

        db.addPerson(p);
        Assert.assertEquals(1, db.getAllPeople().size());

        db.removePerson(p);
        Assert.assertTrue(db.getAllPeople().isEmpty());
    }

    @Test
    public void test_dbRetrievesCorrectPerson() {
        Person p1 = new Person("Ali", "Anwar");
        Person p2 = new Person("Jens", "De Hoog");
        Person p3 = new Person("Matthieu", "Larose");

        PeopleDB db = PeopleDB.getInstance();
        db.addPerson(p1);
        db.addPerson(p2);
        db.addPerson(p3);

        Person retrieved = db.getPerson(p2.getId());
        Assert.assertEquals(p2, retrieved);
    }

}
