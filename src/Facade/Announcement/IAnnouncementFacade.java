package Facade.Announcement;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.Task;

import java.util.ArrayList;
import java.util.List;

public interface IAnnouncementFacade {
    boolean addAnnouncement(AbstractAnnouncement announcement);
    boolean modifyAnnouncement(AbstractAnnouncement announcement);
    boolean deleteAnnouncement(AbstractAnnouncement announcement);

    boolean getAllAnnouncements();

    List<AbstractAnnouncement> getListAnnouncements();

    AbstractAnnouncement getAnnouncementById(int id);

    List<AbstractAnnouncement> getAnnouncementByTitle(String title);
}
