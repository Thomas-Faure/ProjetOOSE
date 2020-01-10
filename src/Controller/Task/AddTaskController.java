package Controller.Task;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import BuisnessLogic.Task.TaskState;
import Facade.SessionFacade;
import Facade.Task.TaskFacade;
import Main.App;
import UI.Announcement.UIAnnouncementManagement;
import UI.Task.UIAddTask;
import UI.Task.UITaskManagement;
import UI.UIError;
import UI.UIGlobalWithController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class AddTaskController{

    @FXML
    private TextField subject;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker deadline;
    @FXML
    private TextField priority;

    private AbstractProject project;
    private UIGlobalWithController ui;

    public AddTaskController(AbstractProject project, UIGlobalWithController ui){
        this.project=project;
        this.ui=ui;
    }

    @FXML
    void addNewTask(ActionEvent actionEvent){
        AbstractTask task = new Task(0,subject.getText(),description.getText(),Integer.parseInt(priority.getText()),deadline.getValue(),SessionFacade.getInstance().getUser(), TaskState.todo,project);
        if(TaskFacade.getInstance().addTask(task)){
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(ui.loadScene().getRoot());
            System.out.println(ui);
            ui.getController().update();

            if(box.getChildren().size() >1 ){
                box.getChildren().remove(1);
            }
        }else{
            UIError error = new UIError(new UIAnnouncementManagement());
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
        }
    }
    @FXML
    void backToTaskPage(ActionEvent actionEvent){
        UITaskManagement task = new UITaskManagement(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());
    }

    @FXML
    void addTaskPage(ActionEvent actionEvent) {
        UIAddTask addTask = new UIAddTask(project,ui);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTask.loadScene().getRoot());
    }
}
