package Controller.Task;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Task.AbstractTask;
import Controller.IController;
import Facade.Task.TaskFacade;
import Main.App;
import UI.Task.*;
import UI.UIError;
import UI.UIGlobal;
import UI.UIGlobalWithController;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TaskManagerController implements Initializable, IController {

    @FXML
    private TextField inputSearch;
    @FXML
    private ListView<AbstractTask> taskList;
    private static AbstractTask toManage;
    //permet de garder la liste de base
    private static ObservableList<AbstractTask> listViewTemp;
    UIGlobalWithController ui;
    private static AbstractProject project;
    public TaskManagerController(AbstractProject project, UIGlobalWithController ui){
        this.project=project;
        this.ui=ui;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        update();

    }
    @FXML
    public void searchBar(KeyEvent keyEvent) {
        if(!(inputSearch.getText().length() == 0)) {
            ArrayList<AbstractTask> array = new ArrayList<>(listViewTemp);
            ArrayList<AbstractTask> toDelete = new ArrayList<>();
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
            for (AbstractTask i : toDelete) {
                array.remove(i);
            }
            ObservableList<AbstractTask> listViewT = FXCollections.observableArrayList(array);
            taskList.setItems(listViewT);
        }else{
            taskList.setItems(listViewTemp);
        }
    }
    public void update(){
        if(taskList != null){
            //si on peut récuperer les taches
            if(TaskFacade.getInstance().getAllTasks(project)) {

                ArrayList<AbstractTask> listeElement = ((ArrayList) TaskFacade.getInstance().getListTasks());
                ObservableList<AbstractTask> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp= FXCollections.observableArrayList(listeElement);
                taskList.setItems(listView);
                taskList.setCellFactory(param -> new Cell(ui));
            }
        }
    }

    public void validation(ActionEvent actionEvent) {

        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(TaskFacade.getInstance().deleteTask(toManage))){
            UIError error = new UIError(new UITaskManagement(project));
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            taskList.getItems().remove(toManage);
            listViewTemp.remove(toManage);
            toManage = null;

        }
    }
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }


    static class Cell extends ListCell<AbstractTask> {
        AbstractTask task;
        HBox hbox = new HBox();
        Image image = new Image("crayon.png");
        ImageView img = new ImageView(image);
        Button btnR = new Button("Read");
        Button btnD = new Button("Delete");
        Button btnM = new Button("Modify");
        Label label = new Label("");
        Pane pane = new Pane();
        UIGlobalWithController ui;

        public Cell(UIGlobalWithController ui){
            super();
            this.ui=ui;
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);
            hbox.getChildren().addAll(img,label,pane,btnR,btnM,btnD);
            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    toManage = task;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
                }
            });
            btnM.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    UIModifyTask modifyTask = new UIModifyTask(task,project,ui);


                    box.getChildren().remove(1);
                    box.getChildren().add(modifyTask.loadScene().getRoot());
                }
            });
            btnR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    UIReadTask read = new UIReadTask(task.getId(),project);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());
                }
            });
        }
        @Override
        public void updateItem(AbstractTask name, boolean empty){
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

    @FXML
    void addTaskPage(ActionEvent actionEvent) {
        UIAddTask addTask = new UIAddTask(project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

        box.getChildren().remove(1);
        box.getChildren().add(addTask.loadScene().getRoot());
    }
}
