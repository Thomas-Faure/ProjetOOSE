package Controller.Sprint;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Sprint.AbstractSprint;
import BuisnessLogic.Sprint.Sprint;
import Facade.SprintFacade;
import Main.App;
import UI.Sprint.SprintUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class AddSprintController {

    @FXML
    private TextField inputSprintName;

    @FXML
    private DatePicker inputBeginDate;

    @FXML
    private DatePicker inputEndDate;

    @FXML
    private Button buttonSave;


    @FXML
    void saveSprint(ActionEvent actionEvent){
        LocalDate localDateBegin = inputBeginDate.getValue();
        LocalDate localDateEnd = inputEndDate.getValue();
        AbstractSprint newSprint = new Sprint(inputSprintName.getText(),localDateBegin,localDateEnd);
        SprintFacade.getInstance().addSprint(newSprint,project.getId());

        SprintUI sprintUI = new SprintUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(sprintUI.loadScene().getRoot());
    }

    private AbstractProject project;

    public AddSprintController(AbstractProject project){
        this.project = project;
    }
}
