package Test.DAO;

import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.GlobalUser;
import DAO.User.GlobalUser.IGlobalUserDAO;
import DAO.User.GlobalUser.GlobalUserDAOMySQL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test de Role du DAO
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class GlobalUserDAOMySQLTest {
    @Test
    void save() {
        IGlobalUserDAO dao = new GlobalUserDAOMySQL();
        AbstractUser user = new GlobalUser(1,"name","firstname","lastname","password");
        boolean valid = dao.save(user);
        assertEquals(true, valid, "to save a new user");
    }
}
