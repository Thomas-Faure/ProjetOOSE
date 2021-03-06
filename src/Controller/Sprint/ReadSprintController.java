package Controller.Sprint;

import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Sprint.AbstractSprint;
import BusinessLogic.Task.AbstractTask;
import BusinessLogic.Task.TaskState;
import Controller.IController;
import Facade.Session.SessionFacade;
import Facade.Sprint.SprintFacade;
import Facade.Task.TaskFacade;
import Main.App;
import UI.Sprint.AddTaskSprintUI;
import UI.Sprint.ReadSprintUI;
import UI.Sprint.SprintUI;
import UI.Task.ModifyTaskUI;
import UI.UIGlobalWithController;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Guillaume Tessier
 */
public class ReadSprintController implements Initializable, IController {


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
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toShow.setVisible(true);
    }

    @FXML
    void backToSprintList(ActionEvent actionEvent){

        SprintUI sprintUI = new SprintUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(sprintUI.loadScene().getRoot());

    }

    @FXML
    public void validation(ActionEvent actionEvent) {

        SprintFacade.getInstance().deleteSprint(sprint.getSprintID());
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

    private AbstractProject project;
    private AbstractSprint sprint;
    UIGlobalWithController ui;


    public ReadSprintController(AbstractProject project, AbstractSprint sprint, UIGlobalWithController ui){
        this.project = project;
        this.sprint = sprint;
        this.ui=ui;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titlePath.setText("Project/"+ project.getName()+"/Sprint/"+sprint.getSprintName());
        sprintNameText.setText(sprint.getSprintName());
        beginDateText.setText("Begin Date : "+sprint.getBeginDate());
        endDateText.setText("Begin End : "+sprint.getEndDate());

        if(!SessionFacade.getInstance().getUser().isAdmin()){
            deleteSprintButton.setDisable(true);
        }

       update();
    }

    @Override
    public void update() {
        //Recuperer les tasks du sprint et les ajouter au Sprint
        TaskFacade.getInstance().getTasksFromSprintId(sprint.getSprintID());
        List<AbstractTask> taskList = TaskFacade.getInstance().getListTasks();
        sprint.setTaskList(taskList);

        List<AbstractTask> taskListTodo  = sprint.getTaskListByState(TaskState.todo);
        List<AbstractTask> taskListDoing  = sprint.getTaskListByState(TaskState.doing);
        List<AbstractTask> taskListDone  = sprint.getTaskListByState(TaskState.done);

        //TO DO
        ObservableList<AbstractTask> listViewTodo = FXCollections.observableArrayList(taskListTodo);
        listViewTemp = FXCollections.observableArrayList(taskListTodo);
        todoSprintList.setItems(listViewTodo);
        todoSprintList.setCellFactory(param -> new ReadSprintController.Cell(project,sprint,ui));

        //DOING
        ObservableList<AbstractTask> listViewDoing = FXCollections.observableArrayList(taskListDoing);
        listViewTemp = FXCollections.observableArrayList(taskListDoing);
        doingSprintList.setItems(listViewDoing);
        doingSprintList.setCellFactory(param -> new ReadSprintController.Cell(project,sprint,ui));

        //DONE
        ObservableList<AbstractTask> listViewDone = FXCollections.observableArrayList(taskListDone);
        listViewTemp = FXCollections.observableArrayList(taskListDone);
        doneSprintList.setItems(listViewDone);
        doneSprintList.setCellFactory(param -> new ReadSprintController.Cell(project,sprint,ui));

    }

    static class Cell extends ListCell<AbstractTask> {
        AbstractSprint sprint;
        AbstractProject project;
        AbstractTask task;
        HBox hbox = new HBox();
        Button btnUpdate = new Button("Update");
        Button btnDelete = new Button("Delete");
        Label label = new Label("");
        Pane pane = new Pane();
        UIGlobalWithController ui;

        public Cell(AbstractProject project,AbstractSprint sprint,UIGlobalWithController ui){
            super();
            this.ui=ui;
            this.project = project;
            this.sprint = sprint;
            hbox.setSpacing(10);
            hbox.getChildren().addAll(label,pane,btnUpdate,btnDelete);
            btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    HBox boxCurrent = (HBox) App.getInstanceScene().lookup("#HBOX");
                    Node currentUI = boxCurrent.getChildren().get(1);

                    ModifyTaskUI updateTaskUI = new ModifyTaskUI(task,project,ui);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    if(box.getChildren().size() >1 )
                        box.getChildren().remove(1);
                    box.getChildren().add(updateTaskUI.loadScene().getRoot());

                    //this.initialize();


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
