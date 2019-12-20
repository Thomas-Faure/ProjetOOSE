package UI.Task;

import BuisnessLogic.Task.Task;
import BuisnessLogic.User.User;
import Controller.TaskController;
import Controller.UILoginController;
import Main.App;
import UI.UIGlobal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskUI implements UIGlobal {



    public TaskUI(){

    }

    public Scene loadScene(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("TaskUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);

        Button btn = (Button) scene.lookup("#addATask");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                UIAddTask addTask = new UIAddTask();
                HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                box.getChildren().remove(1);
                box.getChildren().add(addTask.loadScene().getRoot());

            }
        });


        return scene;
    }


}
