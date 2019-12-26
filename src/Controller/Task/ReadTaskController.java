package Controller.Task;

import BuisnessLogic.Task.AbstractTask;

import Facade.TaskFacade;
import Main.App;
import UI.Task.TaskUI;

import UI.Task.UITaskManagement;
import UI.UIError;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;

import java.net.URL;

import java.util.ResourceBundle;

public class ReadTaskController implements Initializable {
    int id;
    public ReadTaskController(int id){

        this.id=id;
    }
    @FXML
    private TextField state;
    @FXML
    private TextField subject;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker deadline;
    @FXML
    private TextField priority;

    @FXML
    void backToTasks(ActionEvent actionEvent){
        UITaskManagement task = new UITaskManagement();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AbstractTask taskToRead = TaskFacade.getInstance().getTaskById(id);

        if(taskToRead!= null) {
            subject.setText(taskToRead.getName());
            description.setText(taskToRead.getDescription());
            deadline.setValue(taskToRead.getDeadline());
            priority.setText(taskToRead.getPriority() + "");
            state.setText(taskToRead.getStateString());
        }else{
            UIError error = new UIError(new UITaskManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);

        }

    }

    public ReadTaskController(){

    }





}
