package Facade;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.Task;

import java.util.ArrayList;
import java.util.List;

public interface IAnnouncementFacade {
    boolean addAnnouncement(Announcement announcement);
    boolean modifyAnnouncement(Announcement announcement);
    boolean deleteAnnouncement(Announcement announcement);

    boolean getAllAnnouncements();

    List<AbstractAnnouncement> getListAnnouncements();

    AbstractAnnouncement getAnnouncementById(int id);

    List<AbstractAnnouncement> getAnnouncementByTitle(String title);
}
