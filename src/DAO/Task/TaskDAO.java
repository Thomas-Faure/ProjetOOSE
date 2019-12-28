package DAO.Task;


import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import java.util.List;

public interface TaskDAO {
	public AbstractTask createTaskById(int id);
	public boolean save(AbstractTask task);
	public boolean update(AbstractTask task);
	boolean delete(int id);
	List<AbstractTask> getAllTasks();
	AbstractTask getTaskById(int id);
	List<AbstractTask> getTaskByName(String name);

}
