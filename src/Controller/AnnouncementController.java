package Controller;

import BuisnessLogic.Announcement.Announcement;
import Facade.AnnouncementFacade;
import Facade.IAnnouncementFacade;
import Facade.SessionFacade;
import Main.App;
import UI.Task.UIAddTask;
import com.mysql.cj.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class AnnouncementController {
    @FXML
    private Button addAnAnnouncement;

    @FXML
    void addAnnouncementPage(ActionEvent actionEvent) {
        UIAddTask addTask = new UIAddTask();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTask.loadScene().getRoot());
    }

    public AnnouncementController(){

    }


    public boolean addAnnouncement(String title,String description){
        Announcement announcement = new Announcement(0,title,description, LocalDate.now(), SessionFacade.getInstance().getUser());
        return AnnouncementFacade.getInstance().addAnnouncement(announcement);
    }
}
