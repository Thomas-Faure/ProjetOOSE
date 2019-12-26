package BuisnessLogic.Announcement;

import BuisnessLogic.User.User;

import java.time.LocalDate;
import java.time.LocalTime;


public class Announcement extends AbstractAnnouncement {
	int id;
	String title;
	String message;
	LocalDate date;
	User user;
	public Announcement(int id,String title,String message,LocalDate date,User user) {
		this.id=id;
		this.title=title;
		this.message=message;
		this.date=date;
		this.user=user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}



	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
