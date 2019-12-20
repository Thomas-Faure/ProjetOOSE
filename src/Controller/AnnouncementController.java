package Controller;

import BuisnessLogic.Announcement.Announcement;
import Facade.AnnouncementFacade;
import Facade.IAnnouncementFacade;
import Facade.SessionFacade;
import com.mysql.cj.Session;

import java.time.LocalDate;

public class AnnouncementController {
    static AnnouncementController instance;
    private AnnouncementController(){

    }
    public static AnnouncementController getInstance(){
        if(instance == null){
            instance= new AnnouncementController();
        }
        return instance;
    }


    public boolean addAnnouncement(String title,String description){
        Announcement announcement = new Announcement(0,title,description, LocalDate.now(), SessionFacade.getInstance().getUser());
        return AnnouncementFacade.getInstance().addAnnouncement(announcement);
    }
}
