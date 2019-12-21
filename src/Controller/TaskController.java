package Controller;

import BuisnessLogic.Task.Task;

import BuisnessLogic.User.User;
import Facade.SessionFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Task.TaskUI;
import UI.Task.UIAddTask;
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

    //AddTaskPage
    @FXML
    private TextField subject;
    @FXML
    private TextField description;
    @FXML
    private TextField deadline;
    @FXML
    private TextField priority;
    @FXML
    private Button backButton;
    @FXML
    private Button addTaskButton;


    @FXML
    void addNewTask(ActionEvent actionEvent){
        Task task = new Task(0,subject.getText(),description.getText(),Integer.parseInt(priority.getText()),LocalDate.now(),SessionFacade.getInstance().getUser());
        if(TaskFacade.getInstance().addTask(task)){
            TaskUI taskP = new TaskUI();
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(taskP.loadScene().getRoot());
        }else{
            //pas ok
        }
    }
    @FXML
    void backToTaskPage(ActionEvent actionEvent){
        TaskUI task = new TaskUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(task.loadScene().getRoot());
    }

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
        Button btn = new Button("Delete");
        Label label = new Label("");
        Pane pane = new Pane();
       // Image profile = new Image("https://www.mkyong.com/image/mypic.jpg");
       // ImageView img = new ImageView(profile);
        public Cell(){
            super();
            hbox.getChildren().addAll(label,pane,btn);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    getListView().getItems().remove(getItem());

                    TaskFacade.getInstance().deleteTask(task);

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
