package Controller.Announcement;
import BusinessLogic.Announcement.AbstractAnnouncement;
import BusinessLogic.Announcement.Announcement;
import Facade.Announcement.AnnouncementFacade;
import Facade.Session.SessionFacade;
import Main.App;
import UI.Announcement.AnnouncementManagementUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

/**
 * Controller of the add announcement page
 * @author Thomas Faure
 */
public class AddAnnouncementController {

    @FXML
    private TextField title;
    @FXML
    private TextArea message;

    public AddAnnouncementController(){
    }

    /**This method get the different inputs values and try to add a new announcement to the database, if a error occured, the error page is called
     * @param actionEvent
     */
    @FXML
    void addNewAnnouncement(ActionEvent actionEvent){
        AbstractAnnouncement announcement = new Announcement(0,title.getText(),message.getText(),LocalDate.now(),SessionFacade.getInstance().getUser());
        if(AnnouncementFacade.getInstance().addAnnouncement(announcement)){
            AnnouncementManagementUI announcementP = new AnnouncementManagementUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(announcementP.loadScene().getRoot());
        }else{
            UIError error = new UIError(new AnnouncementManagementUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }

    /**
     * Method call by the back button from the UI, to generate the "announcement management" page
     * @param actionEvent
     */
    @FXML
    void backToAnnouncementManagerPage(ActionEvent actionEvent){
        AnnouncementManagementUI announcementManagement = new AnnouncementManagementUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcementManagement.loadScene().getRoot());
    }

}
