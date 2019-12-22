package Controller.Announcement;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import BuisnessLogic.User.User;

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


import javax.xml.soap.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyAnnouncementController implements Initializable {

    int id;
    public ModifyAnnouncementController(int id){
        this.id=id;

    }


    @FXML
    private TextField title;

    @FXML
    private TextArea message;




    @FXML
    void modifyAnAnnouncement(ActionEvent actionEvent){
        AbstractAnnouncement announcement = AnnouncementFacade.getInstance().getAnnouncementById(id);
        announcement.setTitle(title.getText());
        announcement.setMessage(message.getText());
        if(AnnouncementFacade.getInstance().modifyAnnouncement((Announcement)announcement)){
            AnnouncementUI announcementUI = new AnnouncementUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(announcementUI.loadScene().getRoot());

        }else{

        }

    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AbstractAnnouncement announcementToModify = AnnouncementFacade.getInstance().getAnnouncementById(id);
        title.setText(announcementToModify.getTitle());
        message.setText(announcementToModify.getMessage());
    }


    public ModifyAnnouncementController(){

    }


    public void backtoAnnouncements(ActionEvent actionEvent) {
        AnnouncementUI announcementUI = new AnnouncementUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcementUI.loadScene().getRoot());
    }
}
