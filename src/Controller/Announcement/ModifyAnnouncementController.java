package Controller.Announcement;

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
    
    public void backtoAnnouncements(ActionEvent actionEvent) {
        UIGlobal announcementUI = new UIAnnouncementManagement();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcementUI.loadScene().getRoot());
    }

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

    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }
}
