package Facade.Task;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import java.util.List;
/**
 * Facade between Task's controllers and database (Task table)
 */
public interface ITaskFacade {

      /**Method to call the addTask method of the DAO
       * @param task
       * @return
       */
      boolean addTask(AbstractTask task);
      /**Method to call the modifyTask method of the DAO
       * @param task
       * @return
       */
      boolean modifyTask(AbstractTask task);

      /**Method to call the deleteTask method of the DAO
       * @param task
       * @return
       */
      boolean deleteTask(AbstractTask task);

      /**Method to call the getTaskByName method of the DAO
       * @param  name is the name of tasks
       * @return
       */
      List<AbstractTask> getTaskByName(String name);

      /**Method to call the getAllTasks method of the DAO and store the returned tasks
       * @return
       */
      boolean getAllTasks();

      /**Method to call the getTasksById method of the DAO
       * @param id is a task id
       * @return
       */
      AbstractTask getTaskById(int id);

      /**Method to call the getAllTasks method of the DAO and store tasks
       * @param project
       * @return
       */
      boolean getAllTasks(AbstractProject project);
      /**Method to call the getTasksFromSprintId method of the DAO and store tasks
       * @param sprintID is a sprint id
       * @return
       */
      boolean getTasksFromSprintId(int sprintID);

      /**Method to call the getAllBacklogTasks method of the DAO and store tasks
       * @param project is a project
       * @return
       */
      boolean getAllBacklogTasks(AbstractProject project);
}
