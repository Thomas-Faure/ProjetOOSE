package Controller;

import BuisnessLogic.Task.Task;

import Facade.SessionFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Task.TaskUI;
import UI.Task.UIAddTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import javax.xml.soap.Text;
import java.time.LocalDate;

public class TaskController {

    //AddTaskPage
    @FXML
    private TextField subject;
    @FXML
    private TextField description;
    @FXML
    private TextField deadline;
    @FXML
    private TextField priority;
    @FXML
    private Button backButton;
    @FXML
    private Button addTaskButton;

    @FXML
    void addNewTask(ActionEvent actionEvent){
        Task task = new Task(0,subject.getText(),description.getText(),Integer.parseInt(priority.getText()),LocalDate.now(),SessionFacade.getInstance().getUser());
        if(TaskFacade.getInstance().addTask(task)){
            //ok
        }else{
            //pas ok
        }
    }
    @FXML
    void backToTaskPage(ActionEvent actionEvent){
        TaskUI task = new TaskUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());
    }

    //Task Page
    @FXML
    private TextField inputSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private ListView taskList;
    @FXML
    private Button addATask;

    public TaskController(){
    }

    @FXML
    void addTaskPage(ActionEvent actionEvent) {
        UIAddTask addTask = new UIAddTask();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTask.loadScene().getRoot());
    }


    public static boolean modifyTask(int id,String subject,String description, int priority, LocalDate deadline){

        Task task = new Task(id,subject,description,priority,deadline,SessionFacade.getInstance().getUser());
        return TaskFacade.getInstance().modifyTask(task);
    }




}
