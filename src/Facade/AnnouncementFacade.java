package Facade;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import DAO.AbstractDAOFactory;
import DAO.AnnouncementDAO;
import DAO.MySQLDAOFactory;
import DAO.TaskDAO;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementFacade implements IAnnouncementFacade {
    private List<AbstractAnnouncement> announcements;
    private AnnouncementDAO dao;
    public static AnnouncementFacade instance;


    private AnnouncementFacade(){
        dao = MySQLDAOFactory.getAnnouncementDAO();
        this.announcements = new ArrayList<>();
    }

    public static AnnouncementFacade getInstance(){
        if(instance ==null){
            instance =new AnnouncementFacade();
        }
        return instance;
    }
    @Override
    public boolean addAnnouncement(Announcement announcement) {
        if(dao.save(announcement)){
            //on ajouter la nouvelle tache à la liste
            this.announcements.add(announcement);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean modifyAnnouncement(Announcement announcement) {
        if(dao.update(announcement)){
            int i = 0;
            while(this.announcements.get(i).getId() != announcement.getId()){
                i++;
            }
            this.announcements.set(i, announcement);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteAnnouncement(Announcement announcement) {
        if(dao.delete(announcement.getId())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean getAllAnnouncements() {
        this.announcements = dao.getAllAnnouncements();
        return true;
    }

    @Override
    public List<AbstractAnnouncement> getListAnnouncements() {
        return this.announcements;
    }

    @Override
    public AbstractAnnouncement getAnnouncementById(int id){
        AbstractAnnouncement announcement;
        announcement = dao.getAnnouncementById(id);
        return announcement;
    }

    @Override
    public List<AbstractAnnouncement> getAnnouncementByTitle(String title){
        List<AbstractAnnouncement> announcement=dao.getAnnouncementByTitle(title);

        return announcement;
    }




}
