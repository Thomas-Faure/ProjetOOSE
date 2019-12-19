package DAO;


import BuisnessLogic.Task.Task;

public interface TaskDAO {
	public Task createTaskById(int id);
	public boolean save(Task task);
	public boolean update(Task task);
	boolean delete(int id);

}
