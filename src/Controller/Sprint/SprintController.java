package Controller.Sprint;

import BuisnessLogic.Ressource.AbstractResource;
import BuisnessLogic.Sprint.AbstractSprint;
import Controller.Resource.DropBoxConnexion;
import Controller.Resource.ResourceController;
import Facade.ISprintFacade;
import Facade.ResourceFacade;
import Facade.SprintFacade;
import Main.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
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
        if(sprintList != null){

            List<AbstractSprint> listeElement = SprintFacade.getInstance().getListSprintByProject(projectID);
            ObservableList<AbstractSprint> listView = FXCollections.observableArrayList(listeElement);
            listViewTemp = FXCollections.observableArrayList(listeElement);
            sprintList.setItems(listView);
            sprintList.setCellFactory(param -> new SprintController.Cell());
        }
    }

    static class Cell extends ListCell<AbstractSprint> {
        AbstractSprint sprint;
        HBox hbox = new HBox();
        Button btnRead = new Button("Read");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell(){
            super();
            hbox.setSpacing(10);
            hbox.getChildren().addAll(label,pane,btnRead);
            btnRead.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.out.println("See sprint !! OK");
                }
            });

        }
        @Override
        public void updateItem(AbstractSprint name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                sprint = name;
                label.setText(name.getSprintName());
                setGraphic(hbox);
            }
        }

    }
}
