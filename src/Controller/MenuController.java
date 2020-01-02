package Controller;

import Main.App;
import UI.Announcement.AnnouncementUI;
import UI.Announcement.UIAnnouncementManagement;
import UI.Idea.IdeaBoxUI;
import UI.Project.ProjectUI;
import UI.Task.UITaskManagement;
import UI.Ticket.MyTicketUI;
import UI.Ticket.TicketUI;
import UI.User.Global.AllUsersUI;
import UI.User.Global.MyAccountUI;
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
    private Button MyTicket;
    @FXML
    private Button menuAccount;

    @FXML
    void goMenuLogin(ActionEvent event){
        /*
        UITaskManagement task = new UITaskManagement();

        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());
        */

    }
    @FXML
    void goMenuTask(ActionEvent event){
        UITaskManagement task = new UITaskManagement();

        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());

    }
    @FXML
    void goMenuProject(ActionEvent event){
        ProjectUI project = new ProjectUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(project.loadScene().getRoot());
    }

    @FXML
    void goMenuAnnouncementManager(ActionEvent event){
        UIAnnouncementManagement announcement = new UIAnnouncementManagement();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcement.loadScene().getRoot());

    }
    @FXML
    void goMenuTicket(ActionEvent event){
        TicketUI ticket = new TicketUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(ticket.loadScene().getRoot());
    }

    @FXML
    void goMyTicket(ActionEvent event){
        MyTicketUI myTicket = new MyTicketUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(myTicket.loadScene().getRoot());
    }
    @FXML
    void goMenuIdea(ActionEvent event){
        IdeaBoxUI ideabox = new IdeaBoxUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(ideabox.loadScene().getRoot());

    }
    @FXML
    void goMenuMyIdea(ActionEvent event){

    }
    @FXML
    void goMenuUser(ActionEvent event){
        AllUsersUI users = new AllUsersUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(users.loadScene().getRoot());

    }
    @FXML
    void goMenuAnnouncement(ActionEvent event){
        AnnouncementUI announcement = new AnnouncementUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcement.loadScene().getRoot());

    }

    public MenuController(){

    }

    public void goMenuAccount(ActionEvent actionEvent) {
        MyAccountUI users = new MyAccountUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(users.loadScene().getRoot());

    }
}
