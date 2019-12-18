package Facade;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import DAO.AbstractDAOFactory;

public class AnnouncementFacade implements IAnnouncementFacade {
    private AbstractAnnouncement[] announcements;
    private AbstractDAOFactory daoFactory;
}
