package BuisnessLogic.Announcement;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;

import java.time.LocalDate;

public abstract class AbstractAnnouncement {

    public abstract int getId();
    public abstract String getTitle();
    public abstract String getMessage();
    public abstract LocalDate getDate();
    public abstract AbstractUser getUser();
    public abstract void setId(int id);
    public abstract void setTitle(String title);
    public abstract void setMessage(String msg);
    public abstract void setDate(LocalDate date);
}
