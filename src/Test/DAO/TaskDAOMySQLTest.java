package Test.DAO;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import BuisnessLogic.Task.TaskState;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;
import DAO.Task.TaskDAO;
import DAO.Task.TaskDAOMySQL;
import Facade.Project.ProjectFacade;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class to test TaskDAO
 */
class TaskDAOMySQLTest {

    /**
     * Method to test the creation of a database row
     */
    @Test
    void save() {
        TaskDAO dao = new TaskDAOMySQL();
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        ProjectFacade.getInstance().getAllProjects();
        AbstractProject project = ProjectFacade.getInstance().getListProjects().get(0);
        AbstractTask task = new Task(1,"name","description",10,LocalDate.now(),user, TaskState.todo,project );

        boolean valid = dao.save(task);
        assertEquals(true, valid, "to save a new task");
    }




}