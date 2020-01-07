package BuisnessLogic.Task;

import java.time.LocalDate;


import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.User.User;


public class Task extends AbstractTask {
	int id;
	Integer idSprint;
	TaskState state;
	String name;
	int priority;
	LocalDate deadline;
	User creator;
	String description;
	AbstractProject project;

	public Task(int id,String name,String description,int priority,LocalDate deadline,User creator,TaskState state,AbstractProject project) {
		this.id=id;
		this.description=description;
		this.name=name;
		this.priority=priority;
		this.deadline=deadline;
		this.creator=creator;
		this.state=state;
		this.project=project;
		this.idSprint=null;
	}
	public Task(int id,String name,String description,int priority,LocalDate deadline,User creator,TaskState state,AbstractProject project,Integer idSprint) {
		this.id=id;
		this.description=description;
		this.name=name;
		this.priority=priority;
		this.deadline=deadline;
		this.creator=creator;
		this.state=state;
		this.project=project;
		this.idSprint=idSprint;
	}

	public void setProject(AbstractProject project){
		this.project=project;
	}
	public AbstractProject getProject(){
		return this.project;
	}

	@Override
	public Integer getIdSprint() {
		return this.idSprint;
	}

	@Override
	public void setIdSprint(int id) {
		this.idSprint = id;
	}

	public void setState(TaskState state){ this.state=state; }
	public TaskState getState(){
		return this.state;
	}
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
	public void setDescription(String description) {
		this.description=description;
	}
	public String getStateString(){ return state.getStatetoString(); }
	public String getDescription() {
		return description;
	}



}
