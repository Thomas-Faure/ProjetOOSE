package Controller.Task;

import BuisnessLogic.Task.Task;

import BuisnessLogic.User.User;
import Facade.SessionFacade;
import Facade.TaskFacade;
import Main.App;
import UI.Task.*;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.xml.soap.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskManagerController implements Initializable {


    //Task Page
    @FXML
    private TextField inputSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private ListView<Task> taskList;
    @FXML
    private Button addATask;

    //permet de garder la liste de base
    private static ObservableList<Task> listViewTemp;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        //uniquement pour la page des taches manager
        if(taskList != null){
            //si on peut récuperer les taches
            if(TaskFacade.getInstance().getAllTasks()) {
                ArrayList<Task> listeElement = ((ArrayList) TaskFacade.getInstance().getListTasks());
                ObservableList<Task> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp= FXCollections.observableArrayList(listeElement);

                taskList.setItems(listView);
                taskList.setCellFactory(param -> new Cell());

            }
        }

    }


    public static void main(String[] args) {
        System.out.println("myvar".matches("(.*)my(.*)"));
    }
    @FXML
    public void testFct(KeyEvent keyEvent) {
        System.out.println(inputSearch.getText().length());
        if(!(inputSearch.getText().length() == 0)) {


            ArrayList<Task> array = new ArrayList<>(listViewTemp);
            ArrayList<Task> toDelete = new ArrayList<>();
            for (int i = 0; i < array.size(); ++i) {

                String inputS =inputSearch.getText();
                if(inputS.charAt(0) == '*'){
                    inputS= "\\"+inputS;
                }
                String regex = "(.*)" + inputS + "(.*)";
                if (array.get(i).getName().matches(regex)) {

                } else {
                    toDelete.add(array.get(i));

                }
            }

            for (Task i : toDelete) {

                array.remove(i);
            }


            ObservableList<Task> listViewT = FXCollections.observableArrayList(array);
            taskList.setItems(listViewT);

        }else{
            taskList.setItems(listViewTemp);
        }

    }


    static class Cell extends ListCell<Task> {
        Task task;
        HBox hbox = new HBox();
        Image image = new Image("crayon.png");
        ImageView img = new ImageView(image);
        Button btnR = new Button("Read");
        Button btnD = new Button("Delete");
        Button btnM = new Button("Modify");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell(){
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);


            hbox.getChildren().addAll(img,label,pane,btnR,btnM,btnD);
            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    getListView().getItems().remove(getItem());
                    listViewTemp.remove(getItem());
                    if(!(TaskFacade.getInstance().deleteTask(task))){
                        UIError error = new UIError(new UITaskManagement());
                        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                        box.getChildren().add(error.loadScene().getRoot());
                        if(box.getChildren().size() >1 )
                            box.getChildren().remove(2);
                    }

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
            btnR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    UIReadTask read = new UIReadTask(task.getId());
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());

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

    public TaskManagerController(){
    }

    @FXML
    void addTaskPage(ActionEvent actionEvent) {
        UIAddTask addTask = new UIAddTask();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTask.loadScene().getRoot());
    }



    void search(String search){
        //effectue un trie pour chercher les annonces
    }




}
