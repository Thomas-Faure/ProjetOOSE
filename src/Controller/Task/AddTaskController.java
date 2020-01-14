package Controller.Task;
/**
 *
 * @author Thomas Faure
 */
import BusinessLogic.Project.AbstractProject;
import BusinessLogic.Task.AbstractTask;
import BusinessLogic.Task.Task;
import BusinessLogic.Task.TaskState;
import Facade.Session.SessionFacade;
import Facade.Task.TaskFacade;
import Main.App;
import UI.Announcement.AnnouncementManagementUI;
import UI.Task.AddTaskUI;
import UI.UIError;
import UI.UIGlobalWithController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the add task page
 */
public class AddTaskController implements Initializable {

    @FXML
    private Text pathIndication;
    @FXML
    private TextField subject;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker deadline;


    @FXML
    private ToggleGroup group;



    private AbstractProject project;
    private UIGlobalWithController ui;

    public AddTaskController(AbstractProject project, UIGlobalWithController ui){
        this.project=project;
        this.ui=ui;
    }

    /**Method called when the user click on the "add task" button, this method try to add a new task on the database, if an error occured, the error page is played
     * @param actionEvent
     */
    @FXML
    void addNewTask(ActionEvent actionEvent){
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();

        AbstractTask task = new Task(0,subject.getText(),description.getText(),Integer.parseInt(toogleGroupValue),deadline.getValue(),SessionFacade.getInstance().getUser(), TaskState.todo,project);
        if(TaskFacade.getInstance().addTask(task)){
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(ui.loadScene().getRoot());

            ui.getController().update();

            if(box.getChildren().size() >1 ){
                box.getChildren().remove(1);
            }
        }else{
            UIError error = new UIError(new AnnouncementManagementUI());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }

    /**Method called when the use click on the "back" button , generate task management page and display it
     * @param actionEvent
     */
    @FXML
    void backToTaskPage(ActionEvent actionEvent){
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        box.getChildren().add(ui.loadScene().getRoot());
        ui.getController().update();

        if(box.getChildren().size() >1 ){
            box.getChildren().remove(1);
        }
    }

    @FXML
    void addTaskPage(ActionEvent actionEvent) {
        AddTaskUI addTask = new AddTaskUI(project,ui);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTask.loadScene().getRoot());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pathIndication.setText("/Projects/" + project.getName()+"/Tasks/add");
    }
}
