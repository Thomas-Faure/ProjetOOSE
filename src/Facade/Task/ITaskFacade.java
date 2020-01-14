package Facade.Task;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Task.AbstractTask;

import java.util.List;
/**
 * Facade between Task's controllers and database (Task table)
 */
public interface ITaskFacade {

      /**Method to call the addTask method of the DAO
       * @param task is a task
       * @return true if the action was successful
       */
      boolean addTask(AbstractTask task);
      /**Method to call the modifyTask method of the DAO
       * @param task is a task
       * @return true if the action was successful
       */
      boolean modifyTask(AbstractTask task);

      /**Method to call the deleteTask method of the DAO
       * @param task is a task
       * @return true if the action was successful
       */
      boolean deleteTask(AbstractTask task);

      /**Method to call the getTaskByName method of the DAO
       * @param  name is the name of tasks
       * @return a list of task
       */
      List<AbstractTask> getTaskByName(String name);

      /**Method to call the getAllTasks method of the DAO and store the returned tasks
       * @return all tasks
       */
      boolean getAllTasks();

      /**Method to call the getTasksById method of the DAO
       * @param id is a task id
       * @return a task
       */
      AbstractTask getTaskById(int id);

      /**Method to call the getAllTasks method of the DAO and store tasks
       * @param project is a project
       * @return all task by project
       */
      boolean getAllTasks(AbstractProject project);
      /**Method to call the getTasksFromSprintId method of the DAO and store tasks
       * @param sprintID is a sprint id
       * @return true if the action was successful
       */
      boolean getTasksFromSprintId(int sprintID);

      /**Method to call the getAllBacklogTasks method of the DAO and store tasks
       * @param project is a project
       * @return true if the action was successful
       */
      boolean getAllBacklogTasks(AbstractProject project);
}
