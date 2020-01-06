package Controller.Sprint;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Sprint.AbstractSprint;
import BuisnessLogic.Task.AbstractTask;
import Facade.Task.TaskFacade;
import Main.App;
import UI.Sprint.ReadSprintUI;
import UI.Task.UIModifyTask;
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

public class AddTaskSprintController implements Initializable {

    @FXML
    private Text titlePath;

    @FXML
    private Text sprintNameText;

    @FXML
    private Button backButton;

    @FXML
    private ListView taskList;

    private static ObservableList<AbstractTask> listViewTemp;

    private AbstractProject project;
    private AbstractSprint sprint;

    public AddTaskSprintController(AbstractProject project, AbstractSprint sprint){
        this.project=project;
        this.sprint=sprint;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlePath.setText("/Sprint/" + project.getName()+"/Backlog");

        TaskFacade.getInstance().getAllTasks(project);
        List<AbstractTask> taskListBacklog = TaskFacade.getInstance().getListTasks();
        List<AbstractTask> taskToRemove = new ArrayList<AbstractTask>();

        for(AbstractTask task: taskListBacklog){
            for(AbstractTask taskSprint: sprint.getTaskList()){
                if (task.getId()==taskSprint.getId()){
                    taskToRemove.add(task);
                }
            }
        }

        taskListBacklog.removeAll(taskToRemove);

        ObservableList<AbstractTask> listView = FXCollections.observableArrayList(taskListBacklog);
        listViewTemp = FXCollections.observableArrayList(taskListBacklog);
        taskList.setItems(listView);
        taskList.setCellFactory(param -> new AddTaskSprintController.Cell(project));
    }


    static class Cell extends ListCell<AbstractTask> {
        AbstractSprint sprint;
        AbstractProject project;
        AbstractTask task;
        HBox hbox = new HBox();
        Button btnAdd = new Button("Add to Sprint: ");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell(AbstractProject project) {
            super();
            this.project = project;
            hbox.setSpacing(10);
            hbox.getChildren().addAll(label, pane, btnAdd);
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
