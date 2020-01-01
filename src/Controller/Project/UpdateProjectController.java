package Controller.Project;

import BuisnessLogic.Meeting.AbstractMeeting;
import BuisnessLogic.Project.AbstractProject;
import Facade.Meeting.IMeetingFacade;
import Facade.Meeting.MeetingFacade;
import Facade.Project.IProjectFacade;
import Facade.Project.ProjectFacade;
import Main.App;
import UI.Meeting.MeetingsUI;
import UI.Project.ProjectUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProjectController implements Initializable {

    private AbstractProject project;
    private IProjectFacade projectFacade = ProjectFacade.getInstance();

    @FXML
    private TextField name;

    @FXML
    private Text pathIndication;

    @FXML
    private TextArea description;

    public UpdateProjectController(){}
    public UpdateProjectController(AbstractProject project){
        this.project=project;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pathIndication.setText("/Projects/" + project.getName() + "/update");
        name.setText(project.getName());
        description.setText(project.getDescription());
    }

    @FXML
    void cancel(ActionEvent actionEvent){
        ProjectUI projectPage = new ProjectUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(projectPage.loadScene().getRoot());
    }

    @FXML
    void updateProject(ActionEvent actionEvent){
        project.setName(name.getText());
        project.setDescription(description.getText());
        if(projectFacade.update(project)){
            ProjectUI projectPage = new ProjectUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(projectPage.loadScene().getRoot());
        }else{
            UIError error = new UIError(new ProjectUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }
}
