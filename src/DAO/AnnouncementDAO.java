package DAO;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.AbstractTask;

import java.util.List;

public interface AnnouncementDAO {
	 Announcement createAnnouncementById(int id);

	 boolean save(Announcement a);
	 boolean update(Announcement a);
	boolean delete(int id);

    List<AbstractAnnouncement> getAllAnnouncements();
	AbstractAnnouncement getAnnouncementById(int id);
	List<AbstractAnnouncement> getAnnouncementByTitle(String title);
}
