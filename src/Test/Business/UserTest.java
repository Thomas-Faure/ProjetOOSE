package Test.Business;

import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test de User de la business Logic
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class UserTest {

    @Test
    public void getUsername(){
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        assertEquals("name", user.getUsername());
    }

    @Test
    public void getFirstName(){
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        assertEquals("firstname", user.getFirstName());
    }

    @Test
    public void setLastName(){
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        user.setLastName("nouveau");
        assertEquals("nouveau", user.getLastName());
    }



}
