package Controller.Announcement;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import Facade.AnnouncementFacade;

import java.util.List;

public class ShowAnnouncementsController {
    //classe pour permettre aux personens de visualiser les taches en cours

    //quand on clique sur le bouton d'une annonce, l'affiche en gros
    void readAnnouncement(){

    }

    //permet de chercher un annonce par son nom
    void searchAnnouncement(String title){
            List<AbstractAnnouncement> AnnouncementsSearched = AnnouncementFacade.getInstance().getAnnouncementByTitle(title);

    }
}
