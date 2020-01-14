package Controller;

import BusinessLogic.User.AbstractUser;
import Facade.Session.SessionFacade;
import Main.App;
import UI.Announcement.AnnouncementUI;
import UI.Announcement.AnnouncementManagementUI;
import UI.Idea.IdeaBoxUI;
import UI.Login.UILogin;
import UI.Project.ProjectUI;
import UI.Ticket.MyTicketUI;
import UI.Ticket.TicketUI;
import UI.User.Global.AllUsersUI;
import UI.User.Global.MyAccountUI;
import UI.WelcomeUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller of the Menu
 * @author Thomas Faure
 */
public class MenuController implements Initializable {

    @FXML
    private Button menuProject;
    @FXML
    private Button menuAnnouncementManager;
    @FXML
    private Button menuAnnouncement;
    @FXML
    private Button menuTicket;
    @FXML
    private Button menuIdea;

    @FXML
    private Button menuUser;
    @FXML
    private Button menuMyTicket;
    @FXML
    private Button menuAccount;

    public MenuController(){

    }

    /**Method called when the user click on "log off", log off the user
     * @param event
     */

    @FXML
    void logOff(ActionEvent event){
        SessionFacade.getInstance().removeUser();
        UILogin login = new UILogin();
        App.setInstanceScene(login.loadScene());
        App.getInstanceStage().show();
    }

    /**Method called when the user click on the application logo, this method show the welcome page
     * @param event
     */
    @FXML
    void goWelcomePage(ActionEvent event){
        initialiseColor();
        WelcomeUI ui = new WelcomeUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(ui.loadScene().getRoot());
    }

    /**Method called when the user click on the project button, show the project management page
     * @param event
     */
    @FXML
    void goMenuProject(ActionEvent event){
        initialiseColor();
        setSelectedColor(menuProject);
        ProjectUI project = new ProjectUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(project.loadScene().getRoot());
    }

    /**Method called when the user click on the announcement button, this method show the announcement management page
     * @param event
     */
    @FXML
    void goMenuAnnouncementManager(ActionEvent event){
        initialiseColor();
        setSelectedColor(menuAnnouncementManager);
        AnnouncementManagementUI announcement = new AnnouncementManagementUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcement.loadScene().getRoot());

    }

    /**Method called when the user click on the ticket button , this method show the ticket page
     * @param event
     */
    @FXML
    void goMenuTicket(ActionEvent event){
        initialiseColor();
        setSelectedColor(menuTicket);
        TicketUI ticket = new TicketUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(ticket.loadScene().getRoot());
    }

    /**Method called when the user click on the My tickets button, this method show the my ticket page
     * @param event
     */
    @FXML
    void goMyTicket(ActionEvent event){
        initialiseColor();
        setSelectedColor(menuMyTicket);
        MyTicketUI myTicket = new MyTicketUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(myTicket.loadScene().getRoot());
    }

    /**Method called when the user click on the idea button, this method show the idea page
     * @param event
     */
    @FXML
    void goMenuIdea(ActionEvent event){
        initialiseColor();
        setSelectedColor(menuIdea);
        IdeaBoxUI ideabox = new IdeaBoxUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(ideabox.loadScene().getRoot());

    }

    /**Method called when the user click on the my idea button, this method show the my idea page
     * @param event
     */
    @FXML
    void goMenuMyIdea(ActionEvent event){

    }

    /**Method called when the user click on the all users button, this method show the all users page
     * @param event
     */
    @FXML
    void goMenuUser(ActionEvent event){
        initialiseColor();
        setSelectedColor(menuUser);
        AllUsersUI users = new AllUsersUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(users.loadScene().getRoot());

    }

    /**Method called when the user click on the announcements button, this method show the announcement page (for non-admin users)
     * @param event
     */
    @FXML
    void goMenuAnnouncement(ActionEvent event){
        initialiseColor();
        setSelectedColor(menuAnnouncement);
        AnnouncementUI announcement = new AnnouncementUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(announcement.loadScene().getRoot());

    }


    /**Method called when the user click on the account button, this method show the account page
     * @param actionEvent
     */
    public void goMenuAccount(ActionEvent actionEvent) {
        initialiseColor();
        setSelectedColor(menuAccount);
        MyAccountUI users = new MyAccountUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(users.loadScene().getRoot());

    }
    public void setSelectedColor(Button button){
        String selectedColor = " #367fa9";
        button.setStyle("-fx-background-color: "+selectedColor+";");
    }

    public void initialiseColor(){
    String initialColor = " #1e282c";

    menuProject.setStyle("-fx-background-color: "+initialColor+";");

    menuAnnouncementManager.setStyle("-fx-background-color: "+initialColor+";");

    menuAnnouncement.setStyle("-fx-background-color: "+initialColor+";");

    menuTicket.setStyle("-fx-background-color: "+initialColor+";");

    menuIdea.setStyle("-fx-background-color: "+initialColor+";");

    menuUser.setStyle("-fx-background-color: "+initialColor+";");

    menuMyTicket.setStyle("-fx-background-color: "+initialColor+";");

    menuAccount.setStyle("-fx-background-color: "+initialColor+";");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialiseColor();

        AbstractUser user = SessionFacade.getInstance().getUser();
        if(!user.isAdmin()){
            //to disable
            menuAnnouncementManager.setDisable(true);
            menuTicket.setDisable(true);
        }
    }
}