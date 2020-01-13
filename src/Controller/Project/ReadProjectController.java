package Controller.Project;

import BuisnessLogic.Chat.AbstractChat;
import BuisnessLogic.Chat.Chat;
import BuisnessLogic.Project.AbstractProject;
import Facade.ChatFacade;
import Facade.Project.IProjectFacade;
import Facade.Project.ProjectFacade;
import Main.App;
import UI.Chat.ChatUI;
import UI.Meeting.MeetingsUI;
import UI.Project.ProjectUI;
import UI.Ressource.ResourceUI;
import UI.Sprint.AddSprintUI;
import UI.Sprint.SprintUI;
import UI.Task.UITaskManagement;
import UI.User.Member.AllMembersUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Ce controller permet de gérer l'UI de lecture d'un projet
 * @author Rémi Salmi
 */
public class ReadProjectController implements Initializable {

    private AbstractProject project;
    private IProjectFacade projectFacade = ProjectFacade.getInstance();

    @FXML
    private Text projectTitle;

    @FXML
    private Text pathIndication;

    @FXML
    private TextArea description;
    @FXML
    private Button btnTasksSprints;

    public ReadProjectController(){}
    public ReadProjectController(AbstractProject project){
        this.project=project;
    }


    /**
     * Initialisation de l'UI
     * @author Rémi Salmi
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pathIndication.setText("/Projects/" + project.getName());
        projectTitle.setText(project.getName());
        description.setText(project.getDescription());
        if(project.isAgile()){
            btnTasksSprints.setText("Sprints");
        }else{
            btnTasksSprints.setText("Tasks");
        }
    }

    /**
     * Permet de retourner à la page précédente
     * @author Rémi Salmi
     */
    @FXML
    void back(ActionEvent actionEvent){
        ProjectUI projectPage = new ProjectUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(projectPage.loadScene().getRoot());
    }

    @FXML

    /**
     * Permet de se diriger vers le chat du projet
     * @author Rémi Salmi
     */
    void goChat(ActionEvent actionEvent){

        if(!ChatFacade.getInstance().getChatByProjectId(project.getId())) {
            AbstractChat chat = new Chat("Chat_"+project.getName(),project.getId());
            ChatFacade.getInstance().addChat(chat);
        }
        ChatUI chatUI = new ChatUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if (box.getChildren().size() > 1)
            box.getChildren().remove(1);
        box.getChildren().add(chatUI.loadScene().getRoot());

    }

    /**
     * Permet de se diriger vers la page de gestion des membres du projet
     * @author Rémi Salmi
     */
    @FXML
    void goMembers(ActionEvent actionEvent){
        AllMembersUI members = new AllMembersUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(members.loadScene().getRoot());
    }

    /**
     * Permet de se driger vers la page des resources du projet
     * @author Rémi Salmi
     */
    @FXML
    void goResources(ActionEvent actionEvent){
        ResourceUI resourceUI = new ResourceUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(resourceUI.loadScene().getRoot());
    }


    /**
     * Permet de se diriger vers la page des meetings du projet
     * @author Rémi Salmi
     */
    @FXML
    void goMeetings(ActionEvent actionEvent){
        MeetingsUI meetingPage = new MeetingsUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(meetingPage.loadScene().getRoot());
    }
    public void goAddSprint(ActionEvent actionEvent) {
        AddSprintUI addSprintPage = new AddSprintUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(addSprintPage.loadScene().getRoot());
    }

    /**
     * Permet de se diriger vers la page des tasks ou sprints du projet
     * @author Rémi Salmi
     */
    public void goTasksSprints(ActionEvent actionEvent) {
        //tasks
        if(!project.isAgile()){
            UITaskManagement taskManagement = new UITaskManagement(project);
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(taskManagement.loadScene().getRoot());
        }
        else{
            SprintUI sprintUI = new SprintUI(project);
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(sprintUI.loadScene().getRoot());
        }
    }
}
