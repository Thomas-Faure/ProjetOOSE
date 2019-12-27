package BuisnessLogic.Idea;


import BuisnessLogic.User.AbstractUser;

public class Idea extends AbstractIdea {

	int id;
	String name;
	String description;
	String subject;
	String state = null;
	AbstractUser creator;
	public Idea(int id, String name,String description,String subject, AbstractUser creator) {
		this.id = id;
		this.name=name;
		this.description=description;
		this.subject=subject;
		this.creator = creator;
		
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public AbstractUser getCreator() {
		return this.creator;
	}

	public String getDescription() {
		return this.description;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getState(){ return this.state;}


	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreator(AbstractUser creator) {
		this.creator = creator;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	//"validated" or "refused"
	public void setState(String state) { this.state = state; }


	public void refuse() {

	}

	public void validate() {

	}
}
