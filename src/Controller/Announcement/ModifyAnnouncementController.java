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
import UI.Announcement.UIAnnouncementManagement;
import UI.Confirm.UIConfirm;
import UI.Task.TaskUI;

import UI.UIError;
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
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        UIConfirm taskPage = new UIConfirm("Announcement","Modify",announcement,box.getChildren().get(1));
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(taskPage.loadScene().getRoot());
        /*
        announcement.setTitle(title.getText());
        announcement.setMessage(message.getText());
        if(AnnouncementFacade.getInstance().modifyAnnouncement((Announcement)announcement)){
            AnnouncementUI announcementUI = new AnnouncementUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(announcementUI.loadScene().getRoot());

        }else{
            UIError error = new UIError(new UIAnnouncementManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(error.loadScene().getRoot());
        }*/

    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AbstractAnnouncement announcementToModify = AnnouncementFacade.getInstance().getAnnouncementById(id);
        if(announcementToModify !=null) {
            title.setText(announcementToModify.getTitle());
            message.setText(announcementToModify.getMessage());
        }
        else{
            UIError error = new UIError(new UIAnnouncementManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);

        }
    }

    public ModifyAnnouncementController(){

    }


    public void backtoAnnouncements(ActionEvent actionEvent) {
        UIAnnouncementManagement announcementUI = new UIAnnouncementManagement();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcementUI.loadScene().getRoot());
    }
}
