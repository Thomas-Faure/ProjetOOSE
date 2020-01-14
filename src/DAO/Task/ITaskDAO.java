package DAO.Task;

/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Task.AbstractTask;

import java.util.List;
/**
 * Interface of the Task DAO
 */
public interface ITaskDAO {
	/**Method to add an announcement on the databse with a task given in parameter
	 * @param task
	 * @return
	 */
	public boolean save(AbstractTask task);

	/**Method to modify an existing task
	 * @param task
	 * @return
	 */
	public boolean update(AbstractTask task);
	boolean delete(int id);

	/**Method to get all the tasks
	 * @return a list of tasks
	 */
	List<AbstractTask> getAllTasks();

	/**Method to get all the backlog tasks (tasks with no sprint on a agile project) of a certain project given in parameter
	 * @param project a project
	 * @return
	 */
    List<AbstractTask> getAllBacklogTasks(AbstractProject project);

	/**Method to get all the tasks of a certain project given in parameter
	 * @param project
	 * @return
	 */
    List<AbstractTask> getAllTasks(AbstractProject project);

	/**Method to get a task from a specific id given in parameter
	 * @param id id of a task
	 * @return
	 */
	AbstractTask getTaskById(int id);

	/**Method to get tasks from a specific name given in parameter
	 * @param name
	 * @return a list of takss
	 */
	List<AbstractTask> getTaskByName(String name);

	/**Method to get tasks from a specific sprint (its id) given in parameter
	 * @param id id of a sprint
	 * @return
	 */
	List<AbstractTask> getTasksFromSprintId(int id);

}
