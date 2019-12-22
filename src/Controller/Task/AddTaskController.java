package Controller.Task;

import BuisnessLogic.Task.Task;

import BuisnessLogic.User.User;
import Facade.SessionFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Task.TaskUI;
import UI.Task.UIAddTask;
import UI.Task.UIModifyTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.xml.soap.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {



    /**
     *
     * Page Add Task
     */
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
            TaskUI taskP = new TaskUI();
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
        TaskUI task = new TaskUI();
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