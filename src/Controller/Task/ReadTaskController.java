package Controller.Task;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Task.AbstractTask;

import Facade.Task.TaskFacade;
import Main.App;
import UI.Task.TaskManagementUI;
import UI.UIError;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the read task page
 */
public class ReadTaskController implements Initializable {
    int id;
    @FXML
    private Text pathIndication;
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

    private AbstractProject project;
    public ReadTaskController(int id, AbstractProject project){
        this.id=id;
        this.project=project;
    }

    /**Method called when the user click on the back button, show the task management page
     * @param actionEvent
     */
    @FXML
    void backToTasks(ActionEvent actionEvent){
        TaskManagementUI task = new TaskManagementUI(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());
    }

    /**Method called when the controller is created, show task's elements in input fields
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pathIndication.setText("/Projects/" + project.getName()+"/Tasks/read/"+id);

        AbstractTask taskToRead = TaskFacade.getInstance().getTaskById(id);
        if(taskToRead!= null) {
            subject.setText(taskToRead.getName());
            description.setText(taskToRead.getDescription());
            deadline.setValue(taskToRead.getDeadline());
            priority.setText(taskToRead.getPriority() + "");
            state.setText(taskToRead.getStateString());
        }else{
            UIError error = new UIError(new TaskManagementUI(project));
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }
    }
}
