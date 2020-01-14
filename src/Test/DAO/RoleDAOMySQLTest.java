package Test.DAO;

import BusinessLogic.Role.AbstractRole;
import BusinessLogic.Role.Role;
import DAO.Role.RoleDAO;
import DAO.Role.RoleDAOMySQL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test de Role du DAO
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class RoleDAOMySQLTest {
    @Test
    void save() {
        RoleDAO dao = new RoleDAOMySQL();
        AbstractRole role = new Role(1, "nom");
        boolean valid = dao.save(role);
        assertEquals(true, valid, "to save a new role");
    }
}
