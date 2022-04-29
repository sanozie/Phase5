import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

    Account user;
    static RepLink app;
    @BeforeAll
    static void init() { app = new RepLink(); }
    
    @BeforeEach
    void setUp() {
        user = new Account();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validUsernameAndPassword1() {
        app.validUsernameAndPassword("Andrew01", "RepsTo100", "RepsTo100");
            assertEquals("Valid", app.validUsernameAndPassword("Andrew01", "RepsTo100", "RepsTo100"));
    }
    @Test
    void validUsernameAndPassword2(){
        app.validUsernameAndPassword("John567","Curling100", "working");
        assertEquals("Invalid : This is an error Message", app.validUsernameAndPassword("John567","Curling100", "working"));
    }
    @Test
    void validUsernameAndPassword3() {
        app.validUsernameAndPassword("RepNumb", "work", "work");
        assertEquals("Invalid : This is an error Message", app.validUsernameAndPassword("RepNumb", "work", "work"));
    }
    @Test
    void validUsernameAndPassword4() {
        app.validUsernameAndPassword("curl", "Curlnator7", "Curlnator7");
        assertEquals("Invalid : This is an error Message", app.validUsernameAndPassword("curl", "Curlnator7", "Curlnator7"));
    }
    @Test
    void validUsernameAndPassword7() {
        app.validUsernameAndPassword(" curl 100", "RepWorks", "RepWorks");
        assertEquals("Invalid : This is an error Message", app.validUsernameAndPassword("curl 100", "RepWorks", "RepWorks"));
    }

    @Test
    void validUsernameAndPassword10() {
        app.validUsernameAndPassword(" curl100", "Rep Work", "Rep Work");
        assertEquals("Invalid : This is an error Message", app.validUsernameAndPassword("curl 100", "Rep Work", "Rep Work"));
    }
}