package Controller.Sprint;

import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Sprint.AbstractSprint;
import Facade.Sprint.SprintFacade;
import Main.App;
import UI.Sprint.SprintUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *
 * @author Guillaume Tessier
 */
public class ModifySprintController implements Initializable {

    private AbstractProject project;
    private AbstractSprint sprint;

    @FXML
    private TextField inputSprintName;

    @FXML
    private DatePicker inputBeginDate;

    @FXML
    private DatePicker inputEndDate;

    @FXML
    private Button buttonSave;

    public ModifySprintController(AbstractProject project, AbstractSprint sprint){
        this.sprint = sprint;
        this.project = project;
    }

    @FXML
    void updateSprint(ActionEvent actionEvent){
        LocalDate localDateBegin = inputBeginDate.getValue();
        LocalDate localDateEnd = inputEndDate.getValue();

        AbstractSprint updateSprint = SprintFacade.getInstance().getSprintById(sprint.getSprintID());
        updateSprint.setSprintName(inputSprintName.getText());
        updateSprint.setBeginDate(localDateBegin);
        updateSprint.setEndDate(localDateEnd);
        SprintFacade.getInstance().updateSprint(updateSprint);

        SprintUI sprintUI = new SprintUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(sprintUI.loadScene().getRoot());


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AbstractSprint currentSprint = SprintFacade.getInstance().getSprintById(sprint.getSprintID());
        if(sprint != null){
            inputSprintName.setText(currentSprint.getSprintName());
            inputBeginDate.setValue(currentSprint.getBeginDate());
            inputEndDate.setValue(currentSprint.getEndDate());
        }

        else{
            UIError error = new UIError(new SprintUI(project));
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }
    }
}
