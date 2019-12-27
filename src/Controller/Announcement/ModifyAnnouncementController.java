package Controller.Announcement;

import BuisnessLogic.Announcement.AbstractAnnouncement;

import Facade.AnnouncementFacade;

import Main.App;

import UI.Announcement.UIAnnouncementManagement;
import UI.Confirm.UIConfirm;


import UI.UIError;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;


import java.net.URL;

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
        UIConfirm confirmPage = new UIConfirm("Announcement","Modify",announcement,box.getChildren().get(1));
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(confirmPage.loadScene().getRoot());


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
