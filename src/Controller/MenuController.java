package Controller;

import Main.App;
import UI.Announcement.AnnouncementUI;
import UI.Task.TaskUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class MenuController {
    @FXML
    private Button menuLogin;
    @FXML
    private Button menuProject;
    @FXML
    private Button menuAnnouncement;
    @FXML
    private Button menuTicket;
    @FXML
    private Button menuIdea;
    @FXML
    private Button menuMyIdea;
    @FXML
    private Button menuUser;

    @FXML
    void goMenuLogin(ActionEvent event){
        TaskUI task = new TaskUI();

        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());

    }
    @FXML
    void goMenuProject(ActionEvent event){

    }
    @FXML
    void goMenuAnnouncement(ActionEvent event){
        AnnouncementUI announcement = new AnnouncementUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcement.loadScene().getRoot());

    }
    @FXML
    void goMenuTicket(ActionEvent event){

    }
    @FXML
    void goMenuIdea(ActionEvent event){

    }
    @FXML
    void goMenuMyIdea(ActionEvent event){

    }
    @FXML
    void goMenuUser(ActionEvent event){

    }

    public MenuController(){

    }


}
