package Test.DAO;
/**
 *
 * @author Thomas Faure
 */
import Facade.SessionFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Test Class to test login
 */
public class UserLoginDAO {

    @Test
    void Connect(){
        String username = "thomas";
        String password = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8";//it's "password" in SHA-1 encryption
        boolean test = SessionFacade.getInstance().login(username,password);
        assertEquals(true, test, "to connect");

    }

}
