package DAO;

import BuisnessLogic.Announcement.Announcement;

public interface AnnouncementDAO {
	 Announcement createAnnouncementById(int id);

	 boolean save(Announcement a);
	 boolean update(Announcement a);
	boolean delete(int id);

}
