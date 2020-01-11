package Controller.Announcement;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Announcement.AbstractAnnouncement;
import Facade.Announcement.AnnouncementFacade;
import Main.App;
import UI.Announcement.AnnouncementUI;
import UI.Announcement.UIAnnouncementManagement;
import UI.Task.UITaskManagement;
import UI.UIError;
import UI.UIGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the modify announcement page
 */
public class ModifyAnnouncementController implements Initializable {

    int id;
    @FXML
    private TextField title;
    @FXML
    private TextArea message;
    private AbstractAnnouncement toModify;

    public ModifyAnnouncementController(){
    }
    public ModifyAnnouncementController(int id){
        this.id=id;

    }

    /**This method is call when a user click on modify button , the confirm panel is displayed
     * @param actionEvent
     */
    @FXML
    void modifyAnAnnouncement(ActionEvent actionEvent){
        AbstractAnnouncement announcement = AnnouncementFacade.getInstance().getAnnouncementById(id);
        announcement.setTitle(title.getText());
        announcement.setMessage(message.getText());
        toModify=announcement;
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toShow.setVisible(true);
    }

    /**
     * Method call when the controller is created
     * @param arg0
     * @param arg1
     */
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


    /**Method call when the user click on the BackButton, this method generate the announcement management page and display it
     * @param actionEvent
     */
    public void backtoAnnouncements(ActionEvent actionEvent) {
        UIGlobal announcementUI = new UIAnnouncementManagement();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcementUI.loadScene().getRoot());
    }

    /**Method call when the user click on the validation button on the confirmation panel, this method apply the modifidication on an announcement, if a error occured the error page is called
     * @param actionEvent
     */
    public void validation(ActionEvent actionEvent) {
        if(AnnouncementFacade.getInstance().modifyAnnouncement(toModify)){
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            UIAnnouncementManagement am = new UIAnnouncementManagement();
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(am.loadScene().getRoot());
        }else{
            UIError error = new UIError(new UIAnnouncementManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }

    /**Method call when the user click on the refuse button on the confirmation page, the confirmation panel become hidden and the modification announcement page is displayed
     * @param actionEvent
     */
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }
}
