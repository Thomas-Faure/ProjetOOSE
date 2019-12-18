package DAO;

import BuisnessLogic.Announcement.Announcement;

public interface AnnouncementDAO {
	public Announcement createAnnouncementById(int id);

	public boolean save(Announcement a);
	public boolean update(Announcement a);
	void delete(int id);

}
