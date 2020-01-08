package Test.DAO;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Project.Project;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import BuisnessLogic.Task.TaskState;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;
import DAO.Announcement.AnnouncementDAO;
import DAO.Announcement.AnnouncementDAOMySQL;
import DAO.Task.TaskDAO;
import DAO.Task.TaskDAOMySQL;
import Facade.Project.ProjectFacade;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskDAOMySQLTest {

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