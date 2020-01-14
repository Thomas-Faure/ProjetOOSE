package Test.DAO;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Task.AbstractTask;
import BusinessLogic.Task.Task;
import BusinessLogic.Task.TaskState;
import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.User;
import DAO.Task.ITaskDAO;
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
        ITaskDAO dao = new TaskDAOMySQL();
        AbstractUser user = new User(1,"name","firstname","lastname","password");
        ProjectFacade.getInstance().getAllProjects();
        AbstractProject project = ProjectFacade.getInstance().getListProjects().get(0);
        AbstractTask task = new Task(1,"name","description",10,LocalDate.now(),user, TaskState.todo,project );

        boolean valid = dao.save(task);
        assertEquals(true, valid, "to save a new task");
    }




}