package Test.Business;

import BusinessLogic.Role.AbstractRole;
import BusinessLogic.Role.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test de Role de la business Logic
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class RoleTest {
    @Test
    public void getName() {

        AbstractRole role = new Role(1, "nom");
        assertEquals("nom", role.getName());
    }

    @Test
    public void getId(){
        AbstractRole role = new Role(1, "nom");
        assertEquals(1, role.getId());
    }

    @Test
    public void setName() {
        AbstractRole role = new Role(1, "nom");
        role.setName("new");
        assertEquals("new", role.getName());
    }

    @Test
    public void setId(){
        AbstractRole role = new Role(1, "nom");
        role.setId(2);
        assertEquals(2, role.getId());
    }
}
