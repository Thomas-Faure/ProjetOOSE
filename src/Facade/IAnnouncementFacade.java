package Facade;

import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.Task;

public interface IAnnouncementFacade {
    boolean addAnnouncement(Announcement announcement);
    boolean modifyAnnouncement(Announcement announcement);
    boolean deleteAnnouncement(Announcement announcement);
}
