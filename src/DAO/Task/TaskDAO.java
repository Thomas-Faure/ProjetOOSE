package DAO.Task;


import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import java.util.List;

public interface TaskDAO {
	public boolean save(AbstractTask task);
	public boolean update(AbstractTask task);
	boolean delete(int id);
	List<AbstractTask> getAllTasks();
	List<AbstractTask> getAllTasks(AbstractProject project);
	AbstractTask getTaskById(int id);
	List<AbstractTask> getTaskByName(String name);

}
