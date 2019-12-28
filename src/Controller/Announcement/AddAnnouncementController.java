package Controller.Announcement;
import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;

import Facade.Announcement.AnnouncementFacade;
import Facade.SessionFacade;

import Main.App;
import UI.Announcement.UIAnnouncementManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddAnnouncementController implements Initializable {



    @FXML
    private TextField title;
    @FXML
    private TextArea message;

    @FXML
    private Button backButton;




    @FXML
    void addNewAnnouncement(ActionEvent actionEvent){
        AbstractAnnouncement announcement = new Announcement(0,title.getText(),message.getText(),LocalDate.now(),SessionFacade.getInstance().getUser());
        if(AnnouncementFacade.getInstance().addAnnouncement(announcement)){
            UIAnnouncementManagement announcementP = new UIAnnouncementManagement();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(announcementP.loadScene().getRoot());
        }else{
            //pas ok
        }
    }



    @FXML
    void backToAnnouncementManagerPage(ActionEvent actionEvent){
        UIAnnouncementManagement announcementManagement = new UIAnnouncementManagement();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcementManagement.loadScene().getRoot());
    }


    public AddAnnouncementController(){
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void returnAction(ActionEvent actionEvent) {

    }
}
