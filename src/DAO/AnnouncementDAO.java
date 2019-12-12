package DAO;

import java.util.Date;

import Announcement.Announcement;
import User.User;

public interface AnnouncementDAO {
	public Announcement createAnnouncementById(int id);

	public boolean save(Announcement a);
	public boolean update(Announcement a);
	void delete(int id);

}
