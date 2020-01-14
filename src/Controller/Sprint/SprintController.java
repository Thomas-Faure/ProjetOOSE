package Controller.Sprint;

import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Sprint.AbstractSprint;
import Facade.Sprint.SprintFacade;
import Main.App;
import UI.Project.ReadProjectUI;
import UI.Sprint.AddSprintUI;
import UI.Sprint.ModifySprintUI;
import UI.Sprint.ReadSprintUI;
import UI.Sprint.SprintUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Guillaume Tessier
 */
public class SprintController implements Initializable {

    @FXML
    private Button buttonCreate;

    @FXML
    private Text titlePath;

    @FXML
    private ListView<AbstractSprint> sprintList;

    private static ObservableList<AbstractSprint> listViewTemp;

    private static AbstractSprint sprintToManage;

    private AbstractProject project;


    public SprintController(AbstractProject project){
        this.project = project;
    }

    @FXML
    void createSprint(ActionEvent actionEvent){
        AddSprintUI AddSprintUI = new AddSprintUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(AddSprintUI.loadScene().getRoot());
    }

    @FXML
    void backToProjectView(ActionEvent actionEvent){
        ReadProjectUI readProject = new ReadProjectUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(readProject.loadScene().getRoot());
    }

    @FXML
    public void validation(ActionEvent actionEvent) {

        SprintFacade.getInstance().deleteSprint(sprintToManage.getSprintID());
        SprintUI sprintUI = new SprintUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(sprintUI.loadScene().getRoot());
    }


    @FXML
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlePath.setText("Project/"+ project.getName()+"/Sprints");
        if(sprintList != null){

            List<AbstractSprint> listeElement = SprintFacade.getInstance().getListSprintByProject(project.getId());
            ObservableList<AbstractSprint> listView = FXCollections.observableArrayList(listeElement);
            listViewTemp = FXCollections.observableArrayList(listeElement);
            sprintList.setItems(listView);
            sprintList.setCellFactory(param -> new SprintController.Cell(project));
        }
    }

    static class Cell extends ListCell<AbstractSprint> {
        AbstractSprint sprint;
        AbstractProject project;
        HBox hbox = new HBox();
        Button btnRead = new Button("Read");
        Button btnUpdate = new Button("Update");
        Button btnDelete = new Button("Delete");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell(AbstractProject project){
            super();
            this.project = project;
            hbox.setSpacing(10);
            hbox.getChildren().addAll(label,pane,btnRead,btnUpdate,btnDelete);
            btnRead.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    ReadSprintUI readSprintUI = new ReadSprintUI(project,sprint);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);
                    box.getChildren().add(readSprintUI.loadScene().getRoot());
                }
            });

            btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    ModifySprintUI updateSprintUI = new ModifySprintUI(project,sprint);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);
                    box.getChildren().add(updateSprintUI.loadScene().getRoot());
                }
            });

            btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    sprintToManage = sprint;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
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
