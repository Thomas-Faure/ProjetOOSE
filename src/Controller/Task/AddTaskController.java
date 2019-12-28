package Controller.Task;

import BuisnessLogic.Task.Task;

import BuisnessLogic.Task.TaskState;
import Facade.SessionFacade;
import Facade.Task.TaskFacade;
import Main.App;
import UI.Task.UIAddTask;
import UI.Task.UITaskManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {



    /**
     *
     * Page Add Task
     */
    @FXML
    private TextField subject;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker deadline;
    @FXML
    private TextField priority;
    @FXML
    private Button backButton;
    @FXML
    private Button addTaskButton;


    @FXML
    void addNewTask(ActionEvent actionEvent){
        Task task = new Task(0,subject.getText(),description.getText(),Integer.parseInt(priority.getText()),deadline.getValue(),SessionFacade.getInstance().getUser(), TaskState.todo);
        if(TaskFacade.getInstance().addTask(task)){
            UITaskManagement taskP = new UITaskManagement();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(taskP.loadScene().getRoot());
        }else{
            //pas ok
        }
    }
    @FXML
    void backToTaskPage(ActionEvent actionEvent){
        UITaskManagement task = new UITaskManagement();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());
    }







    public AddTaskController(){
    }

    @FXML
    void addTaskPage(ActionEvent actionEvent) {
        UIAddTask addTask = new UIAddTask();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTask.loadScene().getRoot());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
