package Controller.Sprint;

import BuisnessLogic.Ressource.AbstractResource;
import BuisnessLogic.Sprint.AbstractSprint;
import Facade.ISprintFacade;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SprintController implements Initializable {

    @FXML
    private Button buttonCreate;

    @FXML
    private ListView<AbstractSprint> sprintList;

    private static ObservableList<AbstractSprint> listViewTemp;

    private int projectID;


    /*public static SprintController instance;
    private ISprintFacade sFacade;*/

    public SprintController(int projectID){
        this.projectID = projectID;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
