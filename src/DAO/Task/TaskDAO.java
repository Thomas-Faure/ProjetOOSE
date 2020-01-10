package DAO.Task;

/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import java.util.List;

public interface TaskDAO {
	public boolean save(AbstractTask task);
	public boolean update(AbstractTask task);
	boolean delete(int id);
	List<AbstractTask> getAllTasks();

    List<AbstractTask> getAllBacklogTasks(AbstractProject project);

    List<AbstractTask> getAllTasks(AbstractProject project);
	AbstractTask getTaskById(int id);
	List<AbstractTask> getTaskByName(String name);
	List<AbstractTask> getTasksFromSprintId(int id);

}
