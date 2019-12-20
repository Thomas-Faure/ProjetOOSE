package UI;

import Main.App;
import UI.Announcement.AnnouncementUI;
import UI.Announcement.UIAddAnnouncement;
import UI.Login.UILogin;
import UI.Task.TaskUI;
import UI.Task.UIAddTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class Menu implements UIGlobal {

    public Scene loadScene(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("PPMUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);

        Button btnLogin = (Button) scene.lookup("#MenuLogin");
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                TaskUI task = new TaskUI();

                HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
                box.getChildren().add(task.loadScene().getRoot());

            }
        });


        Button btnProject = (Button) scene.lookup("#MenuProject");
        btnProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


            }
        });

        Button btnAnnouncement = (Button) scene.lookup("#MenuAnnouncement");
        btnAnnouncement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                AnnouncementUI announcement = new AnnouncementUI();
                HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
                box.getChildren().add(announcement.loadScene().getRoot());
            }
        });

        Button btnTicket = (Button) scene.lookup("#MenuTicket");
        btnTicket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


            }
        });

        Button btnIdea = (Button) scene.lookup("#MenuIdea");
        btnIdea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


            }
        });

        Button btnMyIdea = (Button) scene.lookup("#MenuMyIdea");
        btnMyIdea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


            }
        });

        Button btnUser = (Button) scene.lookup("#MenuUser");
        btnUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


            }
        });

        return scene;
    }
}
