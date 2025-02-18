import main.model.Person;
import org.junit.jupiter.api.BeforeEach;

class PersonTest {
    Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Buddy", "Wassisname", "1981-01-31", "(709) 555-1234");
    }

//    @Test
//    void calulateAge() {
//        //this test will not work after Jan 2026...
//        int expected = 44;
//        assertEquals(expected, person.calculateAge(person.getBirthdate()));
//    }

}