package BusinessLogic.Announcement;

import BusinessLogic.User.AbstractUser;

import java.time.LocalDate;

/**
 * The announcement class (here the abstract)
 * @author ThomasFaure
 */
public abstract class AbstractAnnouncement {

    /**Method to return the announcement id
     * @return
     */
    public abstract int getId();

    /**Method to return the title of an announcement
     * @return
     */
    public abstract String getTitle();

    /**Method to return the message of an announcement
     * @return
     */
    public abstract String getMessage();

    /**Method to return the dateo of an announcement
     * @return
     */
    public abstract LocalDate getDate();

    /**Method to return the creator of an announcement
     * @return
     */
    public abstract AbstractUser getUser();

    /**Method to set the id of an announcement
     * @param id
     */
    public abstract void setId(int id);

    /**Method to set the title of an announcement
     * @param title
     */
    public abstract void setTitle(String title);

    /**Method to set the message of an announcement
     * @param msg
     */
    public abstract void setMessage(String msg);

    /**Method to set the date of an announcement
     * @param date
     */
    public abstract void setDate(LocalDate date);
}
