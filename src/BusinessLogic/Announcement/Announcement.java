package BusinessLogic.Announcement;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.User;

import java.time.LocalDate;


public class Announcement extends AbstractAnnouncement {
	private int id;
	private String title;
	private String message;
	private LocalDate date;
	private AbstractUser user;
	public Announcement(int id,String title,String message,LocalDate date,AbstractUser user) {
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
	public AbstractUser getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
