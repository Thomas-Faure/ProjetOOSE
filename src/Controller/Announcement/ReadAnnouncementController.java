package Controller.Announcement;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Announcement.AbstractAnnouncement;
import Facade.Announcement.AnnouncementFacade;
import Main.App;
import UI.Announcement.AnnouncementUI;
import UI.Announcement.AnnouncementManagementUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the read announcement page
 */
public class ReadAnnouncementController implements Initializable {
    int id;
    @FXML
    private Text pathIndication;
    @FXML
    private TextField title;
    @FXML
    private TextArea message;
    boolean adminPanel=false;

    public ReadAnnouncementController(){}
    public ReadAnnouncementController(int id,boolean adminPanel){
        this.id=id; this.adminPanel=adminPanel;
    }

    /**
     * Method call when the controller is created
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pathIndication.setText("/Announcement/read/"+id);

        AbstractAnnouncement AnnouncementToRead = AnnouncementFacade.getInstance().getAnnouncementById(id);
        if(AnnouncementToRead != null) {
            title.setText(AnnouncementToRead.getTitle());
            message.setText(AnnouncementToRead.getMessage());
        }else{
            UIError error;
            if(!adminPanel){
                error = new UIError(new AnnouncementUI());
            }else{
                error = new UIError(new AnnouncementManagementUI());
            }
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }
    }

    /**Method call when the user click on the BackButton, this method generate the announcement management page and display it
     * @param actionEvent
     */
    @FXML
    public void backtoAnnouncements(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        if(adminPanel) {
            AnnouncementManagementUI announcement = new AnnouncementManagementUI();
            box.getChildren().add(announcement.loadScene().getRoot());
        }else{
            AnnouncementUI announcement = new AnnouncementUI();
            box.getChildren().add(announcement.loadScene().getRoot());
        }
    }
}
