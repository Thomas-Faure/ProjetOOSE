package Controller.Announcement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.Task;

import BuisnessLogic.User.User;
import Facade.AnnouncementFacade;
import Facade.SessionFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Announcement.UIAnnouncementManagement;
import UI.Task.TaskUI;
import UI.Task.UIAddTask;
import UI.Task.UIModifyTask;
import UI.Task.UITaskManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.xml.soap.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddAnnouncementController implements Initializable {



    /**
     *
     * Page Add Task
     */
    @FXML
    private TextField title;
    @FXML
    private TextArea message;

    @FXML
    private Button backButton;
    @FXML
    private Button addTaskButton;



    @FXML
    void addNewAnnouncement(ActionEvent actionEvent){
        Announcement announcement = new Announcement(0,title.getText(),message.getText(),LocalDate.now(),SessionFacade.getInstance().getUser());
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
