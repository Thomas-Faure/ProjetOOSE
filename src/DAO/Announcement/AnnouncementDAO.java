package DAO.Announcement;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.AbstractTask;

import java.util.List;

public interface AnnouncementDAO {
	 boolean save(AbstractAnnouncement a);
	 boolean update(AbstractAnnouncement a);
	 boolean delete(int id);
	 List<AbstractAnnouncement> getAllAnnouncements();
	 AbstractAnnouncement getAnnouncementById(int id);
	 List<AbstractAnnouncement> getAnnouncementsByTitle(String title);
}
