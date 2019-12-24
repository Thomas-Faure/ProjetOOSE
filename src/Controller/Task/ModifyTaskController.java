package Controller.Task;

import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import BuisnessLogic.User.User;
import Facade.SessionFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Confirm.UIConfirm;
import UI.Task.TaskUI;
import UI.Task.UIAddTask;
import UI.Task.UIModifyTask;
import UI.Task.UITaskManagement;
import UI.UIError;
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

public class ModifyTaskController implements Initializable {

    int id;
    public ModifyTaskController(int id){
        this.id=id;
        System.out.println(this.id+" voila");
    }

    @FXML
    private Text TaskIDField;
    @FXML
    private TextField modifySubject;

    @FXML
    private TextField modifyDescription;
    @FXML
    private TextField modifyDeadline;
    @FXML
    private TextField modifyPriority;
    @FXML
    private Button modifyTaskButton;
    @FXML
    private Button backButton;


    @FXML

    void backToTasks(ActionEvent actionEvent){
        UITaskManagement task = new UITaskManagement();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());
    }
    @FXML
    void modifyATask(ActionEvent actionEvent){
        Task task = new Task(id,modifySubject.getText(),modifyDescription.getText(),Integer.parseInt(modifyPriority.getText()),LocalDate.now(),new User(3,"thomas","faure","faure","faure"));

        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        UIConfirm taskPage = new UIConfirm("Task","Modify",task,box.getChildren().get(1));
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(taskPage.loadScene().getRoot());
        /**if(TaskFacade.getInstance().modifyTask(task)){
            UITaskManagement taskPage = new UITaskManagement();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(taskPage.loadScene().getRoot());
        }else{

            UIError error = new UIError(new UITaskManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }**/

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println(this.id);
        AbstractTask taskToModify = TaskFacade.getInstance().getTaskById(id);
        System.out.println(taskToModify);
        if(taskToModify != null) {
            modifySubject.setText(taskToModify.getName());
            modifyDescription.setText(taskToModify.getDescription());
            modifyDeadline.setText("");
            modifyPriority.setText(taskToModify.getPriority() + "");
        }else{
            System.out.println("on affiche la page classique");
            UIError error = new UIError(new UITaskManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);

        }

    }


    public ModifyTaskController(){

    }





}
