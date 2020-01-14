package Test.DAO;

import BusinessLogic.Idea.AbstractIdea;
import BusinessLogic.Idea.Idea;
import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.User;
import DAO.Idea.IdeaDAO;
import DAO.Idea.IdeaDAOMySQL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test de Idea du DAO
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class IdeaDAOMySQLTest {
    @Test
    void save() {
        IdeaDAO dao = new IdeaDAOMySQL();
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        AbstractIdea idea = new Idea(1,"nom","description", "Subject", user, "validated");
        boolean valid = dao.save(idea);
        assertEquals(true, valid, "to save a new idea");
    }
}
