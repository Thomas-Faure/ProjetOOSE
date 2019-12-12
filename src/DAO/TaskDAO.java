package DAO;

import java.util.Date;

import Announcement.Announcement;
import Task.Task;
import User.User;

public interface TaskDAO {
	public Task createTaskById(int id);
	public boolean save(Task task);
	public boolean update(Task task);
	void delete(int id);

}
