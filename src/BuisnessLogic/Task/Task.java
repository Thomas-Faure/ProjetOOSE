package BuisnessLogic.Task;
/**
 *
 * @author Thomas Faure
 */
import java.time.LocalDate;


import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;


public class Task extends AbstractTask {
	private int id;
	private Integer idSprint;
	private TaskState state;
	private String name;
	private int priority;
	private LocalDate deadline;
	private AbstractUser creator;
	private String description;
	private AbstractProject project;

	public Task(int id,String name,String description,int priority,LocalDate deadline,AbstractUser creator,TaskState state,AbstractProject project) {
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
	public Task(int id,String name,String description,int priority,LocalDate deadline,AbstractUser creator,TaskState state,AbstractProject project,Integer idSprint) {
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

	public Integer getIdSprint() {
		return this.idSprint;
	}
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
	public AbstractUser getCreator() {
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
