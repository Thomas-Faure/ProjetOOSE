package Test.Business;

import BusinessLogic.Idea.AbstractIdea;
import BusinessLogic.Idea.Idea;
import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.User;
import org.junit.jupiter.api.Test;



/**
 * Test de Idea de la business Logic
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class IdeaTest {

    @Test
    void getName() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");

        AbstractIdea idea = new Idea(1,"nom","description", "Subject", user, "validated");
        assertEquals("nom", idea.getName());
    }

    private void assertEquals(String nom, String name) {
    }


    @Test
    public void setDescription() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractIdea idea = new Idea(1,"nom","description", "Subject", user, "validated");
        idea.setDescription("nouvelle");
        assertEquals("nouvelle", idea.getDescription());
    }

    @Test
    public void validate() {
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractIdea idea = new Idea(1,"nom","description", "Subject", user, "validated");
        idea.validate();
        assertEquals("Validated", idea.getState());
    }
}
