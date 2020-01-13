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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller of the modify task page
 */
public class ModifyTaskController implements Initializable {

    AbstractTask task;
    @FXML
    private TextField modifySubject;
    @FXML
    private TextArea modifyDescription;
    @FXML
    private DatePicker modifyDeadline;
    @FXML
    private ToggleGroup group;
    @FXML
    private ChoiceBox stateChoiceBox;

    @FXML
    private RadioButton b1;
    @FXML
    private RadioButton b2;
    @FXML
    private RadioButton b3;
    @FXML
    private RadioButton b4;
    @FXML
    private RadioButton b5;
    @FXML
    private RadioButton b6;
    @FXML
    private RadioButton b7;
    @FXML
    private RadioButton b8;
    @FXML
    private RadioButton b9;
    @FXML
    private RadioButton b10;
    private static AbstractTask toModify;
    private AbstractProject project;

    private UIGlobalWithController ui;
    public ModifyTaskController(){
    }
    public ModifyTaskController(AbstractTask task, AbstractProject project, UIGlobalWithController ui){
        this.task=task;this.project=project;
        this.ui=ui;

    }

    /**Method called when the user click on the "back" button, display the page from the "ui" attribute
     * @param actionEvent
     */
    @FXML
    void backToTasks(ActionEvent actionEvent){
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(ui.loadScene().getRoot());

            ui.getController().update();
            if(box.getChildren().size() >1 ){
                box.getChildren().remove(1);
            }


    }

    /**Method called when the user click on the modify button, this method show the confirmation panel
     * @param actionEvent
     */
    @FXML
    void modifyATask(ActionEvent actionEvent){
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        if(task != null) {
            task.setName(modifySubject.getText());
            task.setDescription(modifyDescription.getText());
            task.setPriority(Integer.parseInt(toogleGroupValue));
            task.setDeadline(modifyDeadline.getValue());
            task.setProject(project);
            task.setState(TaskState.getStateByString((String) stateChoiceBox.getSelectionModel().getSelectedItem()));
            toModify = task;
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toShow.setVisible(true);
        }
    }

    /**Method called when the controller is created, show task's element on the different inputs
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        for(TaskState st : TaskState.values()){
            stateChoiceBox.getItems().add(st.getStatetoString());
        }

        if(task != null) {
            List<Toggle> list = group.getToggles();
            RadioButton selectedRadioButton;
            for(Toggle toggle : list){
                RadioButton toggleButton = (RadioButton) toggle;
                if(Integer.parseInt(toggleButton.getText())  == task.getPriority()){
                    selectedRadioButton = toggleButton;
                    selectedRadioButton.setSelected(true);
                }
            }
            stateChoiceBox.getSelectionModel().select(task.getStateString());
            modifySubject.setText(task.getName());
            modifyDescription.setText(task.getDescription());
            modifyDeadline.setValue(task.getDeadline());

        }else{
            UIError error = new UIError(new UITaskManagement(project));
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }
    }


    /**Method called when the user click on confim button on the confirmation page, this method try to modify the database, if an error occured the error page is displayed
     * @param actionEvent
     */
    public void validation(ActionEvent actionEvent) {
        if(TaskFacade.getInstance().modifyTask((Task)toModify)){

            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(ui.loadScene().getRoot());

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

    /**Method called when the user click on cancel button on the confirmation panel, hide the confirmation panel and show the modify task panel
     * @param actionEvent
     */
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }
}
