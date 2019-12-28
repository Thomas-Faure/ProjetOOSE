package Controller.Task;

import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;

import BuisnessLogic.Task.TaskState;
import BuisnessLogic.User.User;
import Facade.Task.TaskFacade;
import Main.App;
import UI.Task.UITaskManagement;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyTaskController implements Initializable {

    int id;
    public ModifyTaskController(int id){
        this.id=id;

    }


    @FXML
    private TextField modifySubject;

    @FXML
    private TextArea modifyDescription;
    @FXML
    private DatePicker modifyDeadline;
    @FXML
    private TextField modifyPriority;
    @FXML
    private Button modifyTaskButton;
    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox stateChoiceBox;
    private static AbstractTask toModify;


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
        Task task = new Task(id,modifySubject.getText(),modifyDescription.getText(),Integer.parseInt(modifyPriority.getText()),modifyDeadline.getValue(),new User(3,"thomas","faure","faure","faure"),TaskState.getStateByString((String)stateChoiceBox.getSelectionModel().getSelectedItem()));

        toModify = task;


        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toShow.setVisible(true);


    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        for(TaskState st : TaskState.values()){
            stateChoiceBox.getItems().add(st.getStatetoString());
        }
        AbstractTask taskToModify = TaskFacade.getInstance().getTaskById(id);


        if(taskToModify != null) {
            stateChoiceBox.getSelectionModel().select(taskToModify.getStateString());
            modifySubject.setText(taskToModify.getName());
            modifyDescription.setText(taskToModify.getDescription());
            modifyDeadline.setValue(taskToModify.getDeadline());
            modifyPriority.setText(taskToModify.getPriority() + "");
        }else{

            UIError error = new UIError(new UITaskManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);

        }

    }

    public ModifyTaskController(){

    }
    public void validation(ActionEvent actionEvent) {
        if(TaskFacade.getInstance().modifyTask((Task)toModify)){
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            UITaskManagement tm = new UITaskManagement();
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(tm.loadScene().getRoot());
        }else{
            UIError error = new UIError(new UITaskManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }

    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }
}
