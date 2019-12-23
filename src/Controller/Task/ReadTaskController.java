package Controller.Task;

import BuisnessLogic.Task.AbstractTask;

import Facade.TaskFacade;
import Main.App;
import UI.Task.TaskUI;

import UI.Task.UITaskManagement;
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
        System.out.println("coucou");
        System.out.println(id+"");
        this.id=id;
    }
    @FXML
    private TextField subject;
    @FXML
    private TextField description;
    @FXML
    private TextField deadline;
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
        AbstractTask taskToModify = TaskFacade.getInstance().getTaskById(id);
        System.out.println(taskToModify.getName());
        subject.setText(taskToModify.getName());
        description.setText(taskToModify.getDescription());
        deadline.setText("");
        priority.setText(taskToModify.getPriority()+"");

    }

    public ReadTaskController(){

    }





}
