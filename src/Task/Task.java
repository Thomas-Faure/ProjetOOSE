package Task;

import java.time.LocalDate;
import java.util.Date;

import User.User;

public class Task implements ITask {
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	String name;
	int priority;
	LocalDate deadline;
	User creator;
	public Task(int id,String name,int priority,LocalDate deadline,User creator) {
		this.id=id;
		this.name=name;
		this.priority=priority;
		this.deadline=deadline;
		this.creator=creator;
	}


}
