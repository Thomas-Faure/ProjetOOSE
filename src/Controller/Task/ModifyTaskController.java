package Controller.Task;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.Task.Task;
import BuisnessLogic.Task.TaskState;
import BuisnessLogic.User.User;
import Facade.Task.TaskFacade;
import Main.App;
import UI.Task.UITaskManagement;
import UI.UIError;
import UI.UIGlobalWithController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyTaskController implements Initializable {

    AbstractTask task;
    @FXML
    private TextField modifySubject;
    @FXML
    private TextArea modifyDescription;
    @FXML
    private DatePicker modifyDeadline;
    @FXML
    private TextField modifyPriority;
    @FXML
    private ChoiceBox stateChoiceBox;
    private static AbstractTask toModify;
    private AbstractProject project;

    private UIGlobalWithController ui;
    public ModifyTaskController(){
    }
    public ModifyTaskController(AbstractTask task, AbstractProject project, UIGlobalWithController ui){
        this.task=task;this.project=project;
        this.ui=ui;

    }

    @FXML
    void backToTasks(ActionEvent actionEvent){
        UITaskManagement task = new UITaskManagement(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);


    }
    @FXML
    void modifyATask(ActionEvent actionEvent){
        if(task != null) {
            task.setName(modifySubject.getText());
            task.setDescription(modifyDescription.getText());
            task.setPriority(Integer.parseInt(modifyPriority.getText()));
            task.setDeadline(modifyDeadline.getValue());
            task.setProject(project);
            task.setState(TaskState.getStateByString((String) stateChoiceBox.getSelectionModel().getSelectedItem()));
            task.setCreator(new User(3, "thomas", "faure", "faure", "faure"));
            toModify = task;
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toShow.setVisible(true);

        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for(TaskState st : TaskState.values()){
            stateChoiceBox.getItems().add(st.getStatetoString());
        }

        if(task != null) {
            stateChoiceBox.getSelectionModel().select(task.getStateString());
            modifySubject.setText(task.getName());
            modifyDescription.setText(task.getDescription());
            modifyDeadline.setValue(task.getDeadline());
            modifyPriority.setText(task.getPriority() + "");
        }else{
            UIError error = new UIError(new UITaskManagement(project));
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }
    }


    public void validation(ActionEvent actionEvent) {
        if(TaskFacade.getInstance().modifyTask((Task)toModify)){

            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(ui.loadScene().getRoot());
            System.out.println(ui);
            ui.getController().update();

            if(box.getChildren().size() >1 ){
                box.getChildren().remove(1);
            }


        }else{
            UIError error = new UIError(new UITaskManagement(project));
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
