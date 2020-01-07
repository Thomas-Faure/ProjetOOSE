package Controller.Sprint;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Sprint.AbstractSprint;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.TaskState;
import Facade.Task.TaskFacade;
import Main.App;
import UI.Sprint.AddTaskSprintUI;
import UI.Sprint.ReadSprintUI;
import UI.Task.UIModifyTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javafx.fxml.FXML;

import javax.swing.*;
import java.net.URL;
import java.util.List;
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

    private static ObservableList<AbstractTask> listViewTemp;

    @FXML
    private Text beginDateText;

    @FXML
    private Text endDateText;

    @FXML
    private Button deleteSprintButton;

    @FXML
    private Button addTaskButton;

    @FXML
    void addTaskSprint(ActionEvent actionEvent){
        AddTaskSprintUI addTaskSprintUI = new AddTaskSprintUI(project,sprint);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(addTaskSprintUI.loadScene().getRoot());
    }

    @FXML
    void deleteSprint(ActionEvent actionEvent) {
        /*SprintFacade.getInstance().deleteSprint(sprint.getSprintID());

        ReadSprintUI readSprintUI = new ReadSprintUI(project,sprint);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(readSprintUI.loadScene().getRoot());*/
    }
    private AbstractProject project;
    private AbstractSprint sprint;

    public ReadSprintController(AbstractProject project, AbstractSprint sprint){
        this.project = project;
        this.sprint = sprint;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlePath.setText("/Sprint/" + project.getName()+"/Read");
        sprintNameText.setText(sprint.getSprintName());
        beginDateText.setText("Begin Date : "+sprint.getBeginDate());
        endDateText.setText("End Date : "+sprint.getEndDate());

        //Recuperer les tasks du sprint et les ajouter au Sprint
        TaskFacade.getInstance().getTasksFromSprintId(sprint.getSprintID());
        List<AbstractTask> taskList = TaskFacade.getInstance().getListTasks();


        /*AbstractTask t1 = new Task(1,"test1","bla",5, LocalDate.now(),new User(),TaskState.todo,project);
        AbstractTask t2 = new Task(2,"test2","bla",5, LocalDate.now(),new User(),TaskState.doing,project);
        AbstractTask t3 = new Task(3,"test3","bla",5, LocalDate.now(),new User(),TaskState.done,project);

        List<AbstractTask> testList = new ArrayList<AbstractTask>();
        testList.add(t1);
        testList.add(t2);
        testList.add(t3);*/

        sprint.setTaskList(taskList);

        List<AbstractTask> taskListTodo  = sprint.getTaskListByState(TaskState.todo);
        List<AbstractTask> taskListDoing  = sprint.getTaskListByState(TaskState.doing);
        List<AbstractTask> taskListDone  = sprint.getTaskListByState(TaskState.done);

        //TO DO
        ObservableList<AbstractTask> listViewTodo = FXCollections.observableArrayList(taskListTodo);
        listViewTemp = FXCollections.observableArrayList(taskListTodo);
        todoSprintList.setItems(listViewTodo);
        todoSprintList.setCellFactory(param -> new ReadSprintController.Cell(project));

        //DOING
        ObservableList<AbstractTask> listViewDoing = FXCollections.observableArrayList(taskListDoing);
        listViewTemp = FXCollections.observableArrayList(taskListDoing);
        doingSprintList.setItems(listViewDoing);
        doingSprintList.setCellFactory(param -> new ReadSprintController.Cell(project));

        //DONE
        ObservableList<AbstractTask> listViewDone = FXCollections.observableArrayList(taskListDone);
        listViewTemp = FXCollections.observableArrayList(taskListDone);
        doneSprintList.setItems(listViewDone);
        doneSprintList.setCellFactory(param -> new ReadSprintController.Cell(project));
    }

    static class Cell extends ListCell<AbstractTask> {
        AbstractSprint sprint;
        AbstractProject project;
        AbstractTask task;
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
            hbox.getChildren().addAll(label,pane,btnUpdate,btnDelete);
            btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    HBox boxCurrent = (HBox) App.getInstanceScene().lookup("#HBOX");
                    Node currentUI = boxCurrent.getChildren().get(1);
                    UIModifyTask updateTaskUI = new UIModifyTask(task.getId(),project,currentUI);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);
                    box.getChildren().add(updateTaskUI.loadScene().getRoot());
                }
            });

            btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    TaskFacade.getInstance().deleteTask(task);

                    ReadSprintUI readSprintUI = new ReadSprintUI(project,sprint);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);
                    box.getChildren().add(readSprintUI.loadScene().getRoot());
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
