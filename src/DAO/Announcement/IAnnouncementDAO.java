package DAO.Announcement;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Announcement.AbstractAnnouncement;

import java.util.List;

/**
 * Interface of the Announcement DAO
 */
public interface IAnnouncementDAO {
	/**
	 * Method to save an AbstractAnnouncement on the database
	 * @param a
	 * @return
	 */
	 boolean save(AbstractAnnouncement a);

	/**Method to update an AbstractAnnouncement on the database
	 * @param a
	 * @return
	 */
	 boolean update(AbstractAnnouncement a);

	/**Method to delete an Announcement from the database by its id
	 * @param id id of the announcement
	 * @return
	 */
	 boolean delete(int id);

	/**Method to get all the announcements from the databse
	 * @return a list of Announcements
	 */
	 List<AbstractAnnouncement> getAllAnnouncements();

	/**Method to get a announcement with a specific id from the database
	 * @param id if of the announcement
	 * @return
	 */
	 AbstractAnnouncement getAnnouncementById(int id);

	/**Method to get all the announcements which have a certain title given in parameter
	 * @param title title of announcement
	 * @return
	 */
	 List<AbstractAnnouncement> getAnnouncementsByTitle(String title);
}
