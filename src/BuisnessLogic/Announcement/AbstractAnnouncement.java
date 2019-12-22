package BuisnessLogic.Announcement;

import BuisnessLogic.User.User;

import java.time.LocalDate;

public abstract class AbstractAnnouncement {

    public abstract int getId();
    public abstract String getTitle();
    public abstract String getMessage();
    public abstract LocalDate getDate();
    public abstract User getUser();
    public abstract void setTitle(String title);
    public abstract void setMessage(String msg);
}
