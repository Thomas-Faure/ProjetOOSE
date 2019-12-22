package Controller.Announcement;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.AbstractTask;

import Facade.AnnouncementFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Announcement.AnnouncementUI;
import UI.Task.TaskUI;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;

import java.net.URL;

import java.util.ResourceBundle;

public class ReadAnnouncementController implements Initializable {
    int id;
    public ReadAnnouncementController(int id){
        this.id=id;
    }
    @FXML
    private TextField title;
    @FXML
    private TextArea message;




    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println(id);
      AbstractAnnouncement AnnouncementToModify = AnnouncementFacade.getInstance().getAnnouncementById(id);
        title.setText(AnnouncementToModify.getTitle());
        message.setText(AnnouncementToModify.getMessage());
    }

    public ReadAnnouncementController(){

    }

    @FXML
    public void backtoAnnouncements(ActionEvent actionEvent) {
        AnnouncementUI announcement = new AnnouncementUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcement.loadScene().getRoot());
    }
}
