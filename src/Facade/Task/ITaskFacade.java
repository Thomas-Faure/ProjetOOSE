package Facade.Task;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import java.util.List;

public interface ITaskFacade {

      boolean addTask(AbstractTask task);

      boolean modifyTask(AbstractTask task);

      boolean deleteTask(AbstractTask task);

      List<AbstractTask> getTaskByName(String name);

      boolean getAllTasks();

      AbstractTask getTaskById(int id);

      boolean getAllTasks(AbstractProject project);

      boolean getTasksFromSprintId(int sprintID);

      boolean getAllBacklogTasks(AbstractProject project);
}
