package Controller.Sprint;

import BuisnessLogic.Sprint.AbstractSprint;
import BuisnessLogic.Sprint.Sprint;
import Facade.SprintFacade;
import Main.App;
import UI.Sprint.ModifySprintUI;
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

public class ModifySprintController implements Initializable {

    private int projectID;
    private int sprintID;

    @FXML
    private TextField inputSprintName;

    @FXML
    private DatePicker inputBeginDate;

    @FXML
    private DatePicker inputEndDate;

    @FXML
    private Button buttonSave;

    public ModifySprintController(int projectID, int sprintID){
        this.sprintID = sprintID;
        this.projectID = projectID;
    }

    @FXML
    void updateSprint(ActionEvent actionEvent){
        LocalDate localDateBegin = inputBeginDate.getValue();
        LocalDate localDateEnd = inputEndDate.getValue();

        AbstractSprint updateSprint = SprintFacade.getInstance().getSprintById(sprintID);
        updateSprint.setSprintName(inputSprintName.getText());
        updateSprint.setBeginDate(localDateBegin);
        updateSprint.setEndDate(localDateEnd);
        SprintFacade.getInstance().updateSprint(updateSprint);

        SprintUI sprintUI = new SprintUI(1);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(sprintUI.loadScene().getRoot());


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AbstractSprint sprint = SprintFacade.getInstance().getSprintById(sprintID);
        if(sprint != null){
            inputSprintName.setText(sprint.getSprintName());
            inputBeginDate.setValue(sprint.getBeginDate());
            inputEndDate.setValue(sprint.getEndDate());
        }

        else{
            UIError error = new UIError(new SprintUI(projectID));
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }
    }
}
