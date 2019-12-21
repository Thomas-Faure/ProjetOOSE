package DAO;


import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import java.util.List;

public interface TaskDAO {
	public Task createTaskById(int id);
	public boolean save(Task task);
	public boolean update(Task task);
	boolean delete(int id);
	List<AbstractTask> getAllTasks();
	AbstractTask getTaskById(int id);

}
