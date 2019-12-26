package Controller.Announcement;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.AbstractTask;

import Facade.AnnouncementFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Announcement.AnnouncementUI;
import UI.Announcement.UIAnnouncementManagement;
import UI.Task.TaskUI;

import UI.UIError;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;

import java.net.URL;

import java.util.ResourceBundle;

public class ReadAnnouncementController implements Initializable {
    int id;

    @FXML
    private TextField title;
    @FXML
    private TextArea message;


    boolean adminPanel=false;
    public ReadAnnouncementController(){

    }
    public ReadAnnouncementController(int id,boolean adminPanel){
        this.id=id; this.adminPanel=adminPanel;
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        AbstractAnnouncement AnnouncementToRead = AnnouncementFacade.getInstance().getAnnouncementById(id);
        if(AnnouncementToRead != null) {
            title.setText(AnnouncementToRead.getTitle());
            message.setText(AnnouncementToRead.getMessage());
        }else{
            UIError error;
            if(!adminPanel){
                error = new UIError(new AnnouncementUI());
            }else{
                error = new UIError(new UIAnnouncementManagement());
            }
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }
    }


    @FXML
    public void backtoAnnouncements(ActionEvent actionEvent) {

        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        if(adminPanel) {
            UIAnnouncementManagement announcement = new UIAnnouncementManagement();
            box.getChildren().add(announcement.loadScene().getRoot());
        }else{
            AnnouncementUI announcement = new AnnouncementUI();
            box.getChildren().add(announcement.loadScene().getRoot());
        }
    }
}
