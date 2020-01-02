package Controller.Project;

import BuisnessLogic.Project.AbstractProject;
import Facade.Project.IProjectFacade;
import Facade.Project.ProjectFacade;
import Main.App;
import UI.Meeting.MeetingsUI;
import UI.Project.ProjectUI;
import UI.Ressource.ResourceUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ReadProjectController implements Initializable {

    private AbstractProject project;
    private IProjectFacade projectFacade = ProjectFacade.getInstance();

    @FXML
    private Text projectTitle;

    @FXML
    private Text pathIndication;

    @FXML
    private TextArea description;

    public ReadProjectController(){}
    public ReadProjectController(AbstractProject project){
        this.project=project;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pathIndication.setText("/Projects/" + project.getName());
        projectTitle.setText(project.getName());
        description.setText(project.getDescription());
    }

    @FXML
    void back(ActionEvent actionEvent){
        ProjectUI projectPage = new ProjectUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(projectPage.loadScene().getRoot());
    }

    @FXML
    void goChat(ActionEvent actionEvent){

    }

    @FXML
    void goMembers(ActionEvent actionEvent){

    }

    @FXML
    void goResources(ActionEvent actionEvent){
        ResourceUI resourceUI = new ResourceUI(project.getId());
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(resourceUI.loadScene().getRoot());
    }


    @FXML
    void goMeetings(ActionEvent actionEvent){
        MeetingsUI meetingPage = new MeetingsUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(meetingPage.loadScene().getRoot());
    }
}
