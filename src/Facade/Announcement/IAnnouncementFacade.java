package Facade.Announcement;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Facade between Annoucement's controllers and database (Announcement table)
 */
public interface IAnnouncementFacade {

    /**Method to call the addAnnoucement method of the DAO
     * @param announcement
     * @return
     */
    boolean addAnnouncement(AbstractAnnouncement announcement);
    /**Method to call the modifyAnnoucement method of the DAO
     * @param announcement
     * @return
     */
    boolean modifyAnnouncement(AbstractAnnouncement announcement);
    /**Method to call the deleteAnnoucement method of the DAO
     * @param announcement
     * @return
     */
    boolean deleteAnnouncement(AbstractAnnouncement announcement);

    /**Method to call the getAllAnnouncement method of the DAO and store them
     * @return
     */
    boolean getAllAnnouncements();

    /**Method to return the list of Announcements
     * @return
     */
    List<AbstractAnnouncement> getListAnnouncements();

    /**Method to call the getAnnouncementById method of the DAO
     * @return
     */
    AbstractAnnouncement getAnnouncementById(int id);

    /**Method to call the getAnnouncementByTitle method of the DAO
     * @return
     */
    List<AbstractAnnouncement> getAnnouncementByTitle(String title);
}
