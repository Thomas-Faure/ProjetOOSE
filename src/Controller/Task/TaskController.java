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

public class TaskController implements Initializable {


    //Task Page
    @FXML
    private TextField inputSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private ListView<Task> taskList;
    @FXML
    private Button addATask;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        //uniquement pour la page des taches manager
        if(taskList != null){
            //si on peut récuperer les taches
            if(TaskFacade.getInstance().getAllTasks()) {
                ArrayList<Task> listeElement = ((ArrayList) TaskFacade.getInstance().getListTasks());
                ObservableList<Task> listView = FXCollections.observableArrayList(listeElement);

                taskList.setItems(listView);
                taskList.setCellFactory(param -> new Cell());
            }
        }

    }



    static class Cell extends ListCell<Task> {
        Task task;
        HBox hbox = new HBox();
        Button btnD = new Button("Delete");
        Button btnM = new Button("Modify");
        Label label = new Label("");
        Pane pane = new Pane();
        // Image profile = new Image("https://www.mkyong.com/image/mypic.jpg");
        // ImageView img = new ImageView(profile);
        public Cell(){
            super();
            hbox.getChildren().addAll(label,pane,btnM,btnD);
            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    getListView().getItems().remove(getItem());

                    TaskFacade.getInstance().deleteTask(task);

                }
            });
            btnM.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    UIModifyTask modifyTask = new UIModifyTask(task.getId());
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                       box.getChildren().add(modifyTask.loadScene().getRoot());

                }
            });


        }
        @Override
        public void updateItem(Task name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);

            if(name != null && !empty){
                task = name;
                label.setText(name.getId()+" "+name.getName());
                setGraphic(hbox);
            }

        }


    }

    public TaskController(){
    }

    @FXML
    void addTaskPage(ActionEvent actionEvent) {
        UIAddTask addTask = new UIAddTask();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTask.loadScene().getRoot());
    }


    public static boolean modifyTask(int id,String subject,String description, int priority, LocalDate deadline){

        Task task = new Task(id,subject,description,priority,deadline,SessionFacade.getInstance().getUser());
        return TaskFacade.getInstance().modifyTask(task);
    }




}
