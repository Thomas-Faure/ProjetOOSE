package Controller.Sprint;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Sprint.AbstractSprint;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class ReadSprintController implements Initializable {


    @FXML
    private Text titlePath;

    @FXML
    private Text sprintNameText;

    @FXML
    private ListView todoSprintList;

    @FXML
    private ListView doingSprintList;

    @FXML
    private ListView doneSprintList;

    @FXML
    private Text beginDateText;

    @FXML
    private Text endDateText;

    @FXML
    private Button deleteSprintButton;

    @FXML
    void deleteSprint(ActionEvent actionEvent) {
        System.out.println("SPRINT TO DELETE !!");
    }
    private AbstractProject project;
    private AbstractSprint sprint;

    public ReadSprintController(AbstractProject project, AbstractSprint sprint){
        this.project = project;
        this.sprint = sprint;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlePath.setText("/Sprint/" + project.getName());
        sprintNameText.setText(sprint.getSprintName());
        beginDateText.setText("Begin Date : "+sprint.getBeginDate());
        endDateText.setText("End Date : "+sprint.getEndDate());
    }
}
