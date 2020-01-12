package Controller.Sprint;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Sprint.AbstractSprint;
import BuisnessLogic.Task.AbstractTask;
import Controller.IController;
import Facade.Task.TaskFacade;
import Main.App;
import UI.Sprint.AddTaskSprintUI;
import UI.Sprint.ReadSprintUI;
import UI.Task.UIAddTask;
import UI.Task.UIModifyTask;
import UI.UIGlobalWithController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Guillaume Tessier
 */
public class AddTaskSprintController implements Initializable, IController {

    @FXML
    private Text titlePath;

    @FXML
    private Text sprintNameText;

    @FXML
    private Button backButton;

    @FXML
    void backToSprint(ActionEvent event){

        ReadSprintUI readSprintUI = new ReadSprintUI(project,sprint);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(readSprintUI.loadScene().getRoot());
    }

    @FXML
    private Button createTaskButton;

    @FXML
    void createTaskBacklog(ActionEvent event){


        UIAddTask createTaskUI = new UIAddTask(project,ui);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(createTaskUI.loadScene().getRoot());

    }

    @FXML
    private ListView taskList;

    private static ObservableList<AbstractTask> listViewTemp;

    private AbstractProject project;
    private AbstractSprint sprint;
    private  UIGlobalWithController ui;

    public AddTaskSprintController(AbstractProject project, AbstractSprint sprint, UIGlobalWithController ui){
        this.project=project;
        this.sprint=sprint;
        this.ui=ui;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlePath.setText("Project/"+ project.getName()+"/Sprint/Backlog");
        sprintNameText.setText(sprint.getSprintName());
        update();

    }

    @Override
    public void update() {
        TaskFacade.getInstance().getAllBacklogTasks(project);
        List<AbstractTask> taskListBacklog = TaskFacade.getInstance().getListTasks();

        ObservableList<AbstractTask> listView = FXCollections.observableArrayList(taskListBacklog);
        listViewTemp = FXCollections.observableArrayList(taskListBacklog);
        taskList.setItems(listView);
        taskList.setCellFactory(param -> new AddTaskSprintController.Cell(project,sprint));
    }


    static class Cell extends ListCell<AbstractTask> {
        AbstractSprint sprint;
        AbstractProject project;
        AbstractTask task;
        HBox hbox = new HBox();
        Button btnAdd = new Button("Add to Sprint: ");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell(AbstractProject project,AbstractSprint sprint) {
            super();
            this.project = project;
            this.sprint = sprint;
            hbox.setSpacing(10);
            hbox.getChildren().addAll(label, pane, btnAdd);
            btnAdd.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    task.setIdSprint(sprint.getSprintID());
                    TaskFacade.getInstance().modifyTask(task);

                    AddTaskSprintUI addTaskSprintUI = new AddTaskSprintUI(project,sprint);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);
                    box.getChildren().add(addTaskSprintUI.loadScene().getRoot());
                }
            });
        }

        @Override
        public void updateItem(AbstractTask name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                task = name;
                label.setText(name.getName());
                setGraphic(hbox);
            }
        }

    }


}
