package Controller.Sprint;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
        System.out.println(inputSprintName.getText());
        LocalDate localDateBegin = inputBeginDate.getValue();
        LocalDate localDateEnd = inputEndDate.getValue();
        System.out.println(localDateBegin);
        System.out.println(localDateEnd);

        System.out.println("SAVED !!!");
    }

    private int projectID;

    public AddSprintController(int projectID){
        this.projectID = projectID;
    }
}
